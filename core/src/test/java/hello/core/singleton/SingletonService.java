package hello.core.singleton;

//싱글톤 만들기
public class SingletonService {

    //클래스명으로 하나만 스태틱형태로 존재하게 된다.
    //1. 스태틱으로 정의한 클래스 객체가 instance에 저장되고
    private static final SingletonService instance  = new SingletonService();

    //3. instance는 이 함수를 통해서만 꺼낼 수 있다
    public static SingletonService getInstance(){
        return instance;
    }
    //2.외부에서 접근하지 못하게 막고 -> 1개의 인스턴스만 만들 수 있다
    private  SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
