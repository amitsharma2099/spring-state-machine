package com.example.spring_state_machine;

public enum OrderState {
    ORDERED,
    SHIPPED,
    OUT_FOR_DELIVERY,
    DELIVERED,
    RETURN,
    PICKUP,
    REFUND_INITIATED,
    EXCHANGE,
    EXCHANGE_COMPLETED,
    CANCELLED,
    REFUND_COMPLETED;
}
