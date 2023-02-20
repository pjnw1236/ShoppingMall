package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    public void cleanUp() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = new Item();

        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);

        System.out.println("=========================================================================================");
        System.out.println(savedItem.toString());
        System.out.println("=========================================================================================");
    }

    public void createItemList() {
        for (int i=1; i<=10; i++) {
            Item item = new Item();

            item.setItemName("테스트 상품" + i);
            item.setPrice(10000 * i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());

            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNameTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemName("테스트 상품1");

        System.out.println("=========================================================================================");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("=========================================================================================");
    }

    @Test
    @DisplayName("상품명 또는 상세설명 조회 테스트")
    public void findByItemNameOrItemDetail() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");

        System.out.println("=========================================================================================");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("=========================================================================================");
    }

    @Test
    @DisplayName("상품 가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(30000);

        System.out.println("=========================================================================================");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("=========================================================================================");
    }

    @Test
    @DisplayName("상품 가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(60000);

        System.out.println("=========================================================================================");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("=========================================================================================");
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");

        System.out.println("=========================================================================================");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("=========================================================================================");
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByItemDetailByNativeTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");

        System.out.println("=========================================================================================");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("=========================================================================================");
    }
}