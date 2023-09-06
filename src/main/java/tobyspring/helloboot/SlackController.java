//package tobyspring.helloboot;
//
//import org.springframework.http.*;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import tobyspring.util.HttpUtil;
//
//@RestController
//public class SlackController {
//    @GetMapping("/today-i-learned")
//    public String getTodayLearnedApi() throws Exception {
//        return getTodayLearned();
//    }
//
//    private String getTodayLearned() {
//        // 타임아웃 설정
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
//        factory.setReadTimeout(5000); // 타임아웃 설정 5초
//
//// RestTemplate 객체 생성
//        RestTemplate restTemplate = new RestTemplate(factory);
//
//// Header 및 Body 설정
//        HttpHeaders headers = new HttpHeaders();
////        headers.set("헤더이름", "값")
//        headers.setContentType(new MediaType("application", "json"));
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
////        body.add("키", "값");
//
//// 설정한 Header와 Body를 가진 HttpEntity 객체 생성
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
//
//// HTTP POST 요청
//        ResponseEntity<String> response = restTemplate.exchange("요청 URL", HttpMethod.POST, entity, String.class);
//
//// HTTP POST 요청에 대한 응답 확인
//        System.out.println("status : " + response.getStatusCode());
//        System.out.println("body : " + response.getBody());
//
//        return null;
//    }
//
//}
