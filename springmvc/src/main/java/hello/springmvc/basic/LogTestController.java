package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //롬복이 제공하는 애노테이션
@RestController
public class LogTestController   {

    //로거 @slf4j사용하면 선언할 필요 없다.
//    private final Logger log = LoggerFactory.getLogger(getClass()); //현재 클래스의 로거

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        //System.out.println("name = " + name);

        //로그 레벨: 프로퍼티로 확인할 로그 레벨을 설정할 수 있다
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        //운영시스템에서도 봐야할 정보
        log.info(" info log = {}",name); //info log = Spring
        //경고
        log.warn("warn log={}",name);
        //에러
        log.error("error log={}",name);

        return "ok"; //restController를 넣으면 리턴 텍스트를 http 바디에 넣어서 그대로 출력하게 해준다.
    }
}
