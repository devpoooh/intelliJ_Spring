package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**http 요청 메시지 - json
 * {"username":"hello","age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody); //바디 메시지를 그대로 가져옴
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); //json으로 변환
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        resp.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}",messageBody); //바디 메시지를 그대로 가져옴

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); //json으로 변환
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        return "ok";
    }

    //objectMapper 생략하고 바로 객체에서 꺼낼 수도 있다.
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("messageBody={}",helloData); //바디 메시지를 그대로 가져옴

        //객체 데이터 꺼내기
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        HelloData data = httpEntity.getBody();
        //객체 데이터 꺼내기
        log.info("username={}, age={}",data.getUsername(),data.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData  data) {
         //객체 데이터 꺼내기
        log.info("username={}, age={}",data.getUsername(),data.getAge());

        return data;
    }

}
