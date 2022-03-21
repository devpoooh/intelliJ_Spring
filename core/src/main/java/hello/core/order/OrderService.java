package hello.core.order;

//주문 서비스
public interface OrderService {
    //주문정보를 받으면 주문 결과를 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
