package com.example.spring_state_machine;

public enum OrderEvent {
    ON_SHIPMENT,
    ON_CANCEL,
    ON_DELIVERY,
    ON_HANDOVER,
    ON_RETURN,
    ITEM_PICKUP,
    INITIATE_REFUND,
    ON_EXCHANGE,
    ON_PAYMENT,
    VALIDATE_ITEM,
    PROCESS_NEW_ITEM;
}
