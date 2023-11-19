package com.spotlightkonex.service;

import com.spotlightkonex.domain.dto.NewsApiDetailResponseDTO;
import com.spotlightkonex.domain.dto.NewsApiResponseDTO;
import com.spotlightkonex.domain.dto.NewsResponseDTO;
import com.spotlightkonex.domain.entity.News;
import com.spotlightkonex.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    @Value("${naver.clientId}")
    String clientId;

    @Value("${naver.clientSecret}")
    String clientSecret;

    public List<NewsResponseDTO> getNews(String companyName) {

//        newsRepository.deleteAll();
//        saveNews();
        List<News> news = newsRepository.findRandomNewsByCompanyName(companyName).orElseThrow();

        return news
                .stream()
                .map(NewsResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 8 * * *") // 스케줄러로 매일 오전 8시마다 실행되도록 설정
    private void saveNewsScheduler() {
        saveNews();
    }

    private void saveNews() {
        List<String> keywords = Arrays.asList("(주)EMB", "HLB사이언스", "KC산업", "SK시그넷", "가이아코퍼레이션", "골프존데카", "관악산업", "(주)굿센", "길교이앤씨", "나눔테크", "나라소프트", "나우코스", "노드메이슨", "노보믹스", "노브메타파마", "다원넥스뷰", "대동고려삼", "대주이엔티", "더콘텐츠온", "데이터스트림즈", "도부마스크", "듀켐바이오", "디피코", "라피치", "럭스피아", "렌딩머신", "로보쓰리에이아이앤로보틱스", "로지스몬", "루켄테크놀러지스", "루트락", "마이크로엔엑스", "메디쎄이", "메디안디노스틱", "메디젠휴먼케어", "무진메디", "미래엔에듀파트너", "(주)미쥬", "바스칸바이오제약", "바이오인프라생명과학", "바이오텐", "바이오프로테크", "(주)베른", "(주)볼빅", "블루탑", "비엘헬스케어", "뿌리깊은나무들", "삼미금속", "셀젠텍", "수프로", "썬테크", "씨알푸드", "(주)씨앗", "씨앤에스링크", "씨엔티드림", "아이엠지티", "아이오바이오", "아이케이세미콘", "아퓨어스", "(주)아하", "안지오랩", "애니메디솔루션", "엄지하우스", "에스알바이오텍", "에스엘테라퓨틱스", "에스엠비나", "에스제이켐", "에스케이씨에스", "에이아이더뉴트리진", "에이원알폼", "에이치엔에스하이텍", "에이펙스인텍", "에피바이오텍", "엔솔바이오사이언스", "엔에스엠", "엔에스컴퍼니", "엔지브이아이", "엘리비젼", "(주)엘에이티", "(주)원포유", "위월드", "유니포인트", "유엑스엔", "이노벡스", "이브이파킹서비스", "이비테크", "이성씨엔아이", "이앤에치", "(주)이엠티", "인바이츠바이오코아", "제노텍", "제이엠멀티", "(주)젬", "(주)지슨", "지앤이헬스케어", "지에프씨생명과학", "진코스텍", "질경이", "카이바이오텍", "(주)켈스", "(주)코나솔", "(주)코셋", "코스텍시스템", "큐라켐", "큐러블", "큐엠씨", "크로넥스", "타스컴", "(주)타이드", "타임기술", "(주)탈로스", "(주)탑선", "태양3C", "태양기계(주)", "테크엔", "테크트랜스", "티엘엔지니어링", "(주)틸론", "파마리서치바이오", "파워풀엑스", "판도라티비", "펨토바이오메드", "퓨쳐메디신", "프로젠", "플럼라인생명과학", "피노텍", "한국미라클피플사", "한국피아이엠", "한중엔시에스", "휴벡셀");
        for (String keyword : keywords) {
            saveKeywordNews(keyword);
        }
    }

    private void saveKeywordNews(String keyword) {

        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + keyword + "&display=20&sort=sim"; // 정확도순

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = new HttpHeaders();
        header.set("X-Naver-Client-Id", clientId);
        header.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity entity = new HttpEntity<>(header);

        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(apiURL);
        URI finalUri = uri.build().encode().toUri();

        NewsApiResponseDTO response = restTemplate.exchange(finalUri, HttpMethod.GET, entity, NewsApiResponseDTO.class).getBody();

        if (response == null) {
            // throw new CustomException(ErrorCode.NEWS_NOT_FOUND);
        }

        for (NewsApiDetailResponseDTO apiDetailResponse : response.getNewsApiDetailResponsDTOS()) {
            String title = replaceText(apiDetailResponse.getTitle());
            String link = apiDetailResponse.getLink();
            String description = replaceText(apiDetailResponse.getDescription());
            String pubDate = apiDetailResponse.getPubDate();

            News news = News.builder()
                    .title(title)
                    .link(link)
                    .description(description)
                    .pubDate(pubDate)
                    .build();

            newsRepository.save(news);
        }
    }

    public static String replaceText(String text){
        return text.replace("<b>", "")
                .replace("</b>", "")
                .replace("&quot;", "\"");
    }

}