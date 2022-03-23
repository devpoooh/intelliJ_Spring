package hello.core.singleton;

public class StatefulService {
    
    private int price; //상태를 유지하는 필드
    
    public int order(String name, int price){
        System.out.println("name = " + name + " price = "+price);
//        this.price =price; //여기서 문제 발생
        return price; //해결! 한 함수 안에서 리턴하게 한다
    }

    /*public int getPrice(){
        return price;
    }*/
}
