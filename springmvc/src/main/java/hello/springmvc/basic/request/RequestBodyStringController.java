package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

//http 요청메시지 단순 텍스트
@Slf4j
@Controller
public class RequestBodyStringController {
    //요청 파라미터 vs HTTP 메시지 바디
    //요청 파라미터를 조회하는 기능: @RequestParam, @modelAttribute
    // HTTP메시지 바디를 직접 조회하는 기능: @RequestBody

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);

        resp.getWriter().write("ok");
    }

    //바로 텍스트 바디 받기
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);

        responseWriter.write("ok");
    }

    //메시지 컨버터
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody(); //메세지 바디를 저장한다.
        log.info("messageBody = {}",messageBody);

        return new HttpEntity<>("ok");
    }

    //애노테이션으로 메시지 바디 가져오기
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){

        log.info("messageBody = {}",messageBody);

        return "ok";
    }
}
