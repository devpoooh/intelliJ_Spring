package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController //view로 연결하지않고 텍스트로 반환한다.
public class RequestHeaderController  {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest req,
                          HttpServletResponse resp,
                          HttpMethod httpMethod,    //요청방식(get,post...)
                          Locale locale,            //언어정보
                          @RequestHeader MultiValueMap<String, String> headerMap,   //헤더정보를 map으로 전부 다 가져온다.
                          @RequestHeader("host") String host,    //헤더정보의 키가 host인 것 만 가져온다.
                          @CookieValue(value = "myCookie", required = false) String cookie //쿠기의 이름이 myCookie이고 required=false 쿠기가 없어도 된다.
                          ){
        log.info("request={}", req); //http의 서블릿 요청
        log.info("response={}", resp); //http의 서블릿 응답
        log.info("httpMethod={}", httpMethod); //GET
        log.info("locale={}", locale);  //ko-KR
        log.info("headerMap={}", headerMap); //헤더의 모든 정보
        log.info("header host={}", host);   //host=localhost:8080
        log.info("myCookie={}", cookie);
        return "ok";
    }
}
