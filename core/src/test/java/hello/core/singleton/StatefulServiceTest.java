package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest  {

    @Test
    @DisplayName("싱글톤의 문제점 및 해결 방법")
    void statefulServiceSingleton(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(TestConfig.class);

        //고객1과 고객2 객체 생성 -> 같은 객체임
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //TheadA: A사용자 10000원 주문
//        statefulService1.order("userA",100000);
        //변경
        int userAPrice = statefulService1.order("userA",100000);
        //TheadB: B사용자 20000원 주문
//        statefulService1.order("userB",200000);
        //변경
        int userBPrice = statefulService1.order("userB",200000);


        //ThreadA: 사용자A 주문 금액 조회하면? --> 당연히 20000원이 나온다.
        //why? 같은 객체(잠조값이 같은)를 사용하고 있기 때문이다
        //int price = statefulService1.getPrice();

        System.out.println("price = " + userAPrice); //A의 값을 제대로 확인할 수 있다.
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}