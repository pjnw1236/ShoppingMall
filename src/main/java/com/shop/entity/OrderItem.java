package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가겨
    private int count; // 수향

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); // 주문할 상품
        orderItem.setCount(count); // 주문 수향
        orderItem.setOrderPrice(item.getPrice()); // 주문 가격
        item.removeStock(count); // 주문 수량만큼 재고에서 차감
        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() {
        this.getItem().addStock(count);
    }
}
