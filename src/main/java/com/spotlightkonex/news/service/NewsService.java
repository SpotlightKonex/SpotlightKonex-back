package com.spotlightkonex.news.service;

import com.spotlightkonex.news.controller.dto.NewsApiDetailResponse;
import com.spotlightkonex.news.controller.dto.NewsApiResponse;
import com.spotlightkonex.news.controller.dto.NewsResponse;
import com.spotlightkonex.news.domain.entity.News;
import com.spotlightkonex.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    public List<NewsResponse> getNews(final String companyName) {

        saveNews(companyName); // 추후 삭제
        List<News> news = newsRepository.findAll();

        return news
                .stream()
                .map(NewsResponse::new)
                .collect(Collectors.toList());
    }

    private void saveNews(String keyword) {

        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=(주)" + keyword + "&display=30&sort=sim"; // 정확도순

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = new HttpHeaders();
        header.set("X-Naver-Client-Id", clientId);
        header.set("X-Naver-Client-Secret", clientSecret);
        HttpEntity entity = new HttpEntity<>(header);

        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(apiURL);
        URI finalUri = uri.build().encode().toUri();

        NewsApiResponse response = restTemplate.exchange(finalUri, HttpMethod.GET, entity, NewsApiResponse.class).getBody();

        if (response == null) {
            // throw new CustomException(ErrorCode.NEWS_NOT_FOUND);
        }

        for (NewsApiDetailResponse apiDetailResponse : response.getNewsApiDetailResponses()) {
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