package hello.core.xml;

import hello.core.discount.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    @DisplayName("xml로 컨테이너 만들기")
    void xmlAppContext(){
        //xml파일로 빈 생성
        ApplicationContext ac =new GenericXmlApplicationContext("appConfig.xml");
        MemberService membersService = ac.getBean("membersService", MemberService.class);
        assertThat(membersService).isInstanceOf(MemberService.class);
    }
}
