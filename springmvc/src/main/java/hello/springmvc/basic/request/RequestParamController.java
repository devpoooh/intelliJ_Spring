package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("username={}, age={}",username,age);

        resp.getWriter().write("ok");
    }

    //매개변수로 파라미터 받기
    @ResponseBody //클래스가 @Controller일 때 반환을 httpBody에 넣고 싶은 텍스트일 때 붙인다
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username = {}, age = {}",memberName,memberAge);

        return "ok";
    }

    //매개변수 괄호 생략
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    //@RequestParam까지 없애기: 단순 타입만 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    //@RequstParam required 속성
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = false) String username, //디폴트는 true
            @RequestParam(required = false) Integer age
    ){
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    //요청 파라미터 기본 값
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = false, defaultValue = "guest") String username, //디폴트는 true
            @RequestParam(required = false, defaultValue = "-1") int age
    ){
        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    //파라미터를 Map으로
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){ //파라미터를 map으로 가져온다
        log.info("username = {}, age = {}",paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    //model 데이터 저장 v1
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        //@ModelAttribute는 helloData 객체를 생성하여 객체를 찾아 알아서 setter를 바인딩해준다.

        log.info("username = {}, age = {}",helloData.getUsername(),helloData.getAge());
        log.info("helloData = {}",helloData); //toString도 가능

        return "ok";
    }

    //@ModelAttribute도 생략 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        //@ModelAttribute는 helloData 객체를 생성하여 객체를 찾아 알아서 setter를 바인딩해준다.

        log.info("username = {}, age = {}",helloData.getUsername(),helloData.getAge());
        log.info("helloData = {}",helloData); //toString도 가능

        return "ok";
    }
}
