package com.internship.apitaskmanagement.enums;

public enum Order {

    // Enum для сортировки
    CREATED_DATE_ASC("createdDateAsc"), CREATED_DATE_DESC("createdDateDesc"),
    TIME_ASC("timeAsc"), TIME_DESC("timeDesc");

    private final String order;

    Order(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
