package com.spotlightkonex.find.domain;

public enum cropName {
    NUM1 { //금속 및 화학 제조업
        public String [] getEnterprise(){
            return new String[]{"1차 철강 제조업", "기초 화학물질 제조업", "기타 금속 가공제품 제조업"};
        }
    },
    NUM2{ //식품 및 섬유 제조업
        public String [] getEnterprise(){
            return new String[] {"곡물가공품, 전분 및 전분제품 제조업", "기타 식품 제조업", "봉제의복 제조업" };
        }
    },
    NUM3{ //전자제품 및 기타 제조업
        public String [] getEnterprise(){
            return new String[] {"반도체 제조업", "사진장비 및 광학기기 제조업", "영상 및 음향기기 제조업", "의료용 기기 제조업", "일차전지 및 축전지 제조업",
                    "전구 및 조명장치 제조업","자동차 신품 부품 제조업" + "자동차 차체 및 트레일러 제조업", "전자부품 제조업", "플라스틱제품 제조업", "합성고무 및 플라스틱 물질 제조업"};
        }
    },
    NUM4{ //도매업
        public String [] getEnterprise(){
            return new String[] {"기계장비 및 관련 물품 도매업", "기타 전문 도매업", "산업용 농·축산물 및 동·식물 도매업", "생활용품 도매업"};
        }
    },
    NUM5{ //서비스업
        public String [] getEnterprise(){
            return new String[] {"건축기술, 엔지니어링 및 관련 기술 서비스업", "교육지원 서비스업", "그외 기타 전문, 과학 및 기술 서비스업", "기타 운송관련 서비스업",
                    "소프트웨어 개발 및 공급업", "영화, 비디오물, 방송프로그램 제작 및 배급업", "자연과학 및 공학 연구개발업", "컴퓨터 프로그래밍, 시스템 통합 및 관리업"};
        }
    },
    NUM6{ //건설 및 공사업
        public String [] getEnterprise(){
            return new String[] {"기반조성 및 시설물 축조관련 전문공사업", "실내건축 및 건축마무리 공사업", "토목 건설업"};
        }
    },
    NUM7{ //금융업
        public String [] getEnterprise(){
            return new String[] {"기타 금융업"};
        }
    },
    NUM8{ //전기 및 전자 관련 업종
        public String [] getEnterprise(){
            return new String[] {"기타 전기장비 제조업", "전동기, 발전기 및 전기 변환 · 공급 · 제어 장치 제조업", "전기 통신업"};
        }
    };
    public abstract String [] getEnterprise();
}
