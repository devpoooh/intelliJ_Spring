package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트 스캔
@Configuration
@ComponentScan(
        //시작점 지정 디폴트는 지금 패키지를 기준으로 한다.(hello.core)
//        basePackages =  "hello.core.member", //member에서만 찾는다. {"hello.core","hello.core.xxx"} 여러개 지정도 가능
        //뺄거를 설정하겠다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //AppConfig에서 수동으로 빈 등록을 해뒀으니 제외하자
)
public class AutoAppConfig {

}

