package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    //hello-basic
    @RequestMapping(value = "/hello-basic") //배열도 가능
    public String helloBasic(){
        log.info("helloBasic");

        return "ok"; //리턴값이 string이면 알아서 httpbody에 넣어준다.
    }

    //HTTP 메서드 매핑
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mappingGetV1");

        return "ok";
    }

    //HTTP 메서드 매핑 축약 get.post,put,delete,patch  제공
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");

        return "ok";
    }

    /**
     * PathVariable: 경로에 변수가 들어갈 때 사용한다.
     * 가져올 변수값과 함수안에서 사용할 변수값이 같으면 생략가능하다.
     * ex) @PathVariable("userId") String userId -> @PathVariable userId
     * ex) /mapping/userA
     */
    //경로 변수
    @GetMapping("/mapping/{userId}") //userA면
    public String mappingPath(@PathVariable String userId){ //해당 패스의 변수를 뽑아서 사용할 수 있다.
        log.info("mappingPath userId = {}",userId);

        return "ok";
    }

    /**
     * PathVariable 다중 사용
     */
    //경로 변수 다중
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}",userId,orderId);

        return "ok";
    }

    /**
     * 파라미터로 추가매핑: 여러가지 표현 가능
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params={"mode!=debug,"data=good"}
     */
    //특정 파라미터 조건 매핑
    @GetMapping(value = "/mapping-param",params = "mode=debug") //파라미터에 mode=debug가 있으면 호출
    public String mappingParam(){
        log.info("mappingParam");

        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    //특정 헤더 조건 매핑(POST MAN 필요)
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");

        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    //미디어 타입 조건 매핑 Content-Type(POST MAN 필요)
    @PostMapping(value = "/mapping-consume", consumes = "application/json") //컨텐트 타입을 지정할 수 있다.
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    //미디어 타입 조건 매핑 Accept(POST MAN 필요)
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
