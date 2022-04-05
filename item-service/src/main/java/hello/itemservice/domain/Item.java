package hello.itemservice.domain;

import lombok.Data;

@Data
public class Item {
     private Long id; //id
    private  String itemName; //상품명
    private Integer price; //가격
    private  Integer quantity; //수량

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
