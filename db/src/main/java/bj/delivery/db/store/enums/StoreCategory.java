package bj.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {

    KOREAN("한식"),
    WESTERN("양식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    CHICKEN("치킨"),
    PIZZA("피자"),
    HAMBURGER("햄버거"),
    COFFEE_TEA("커피&차");

    private final String description;
}
