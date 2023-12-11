package com.internship.apitaskmanagement.enums;

public enum Order {

    // Enum для сортировки
    CREATED_DATE_ASC("createdDateAsc"), CREATED_DATE_DESC("createdDateDesc");

    private final String order;

    Order(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
