package com.example.spring_state_machine;

import org.springframework.statemachine.StateMachine;

public class StateMachineExample {
    StateMachine<OrderState,OrderEvent> stateMachine;

    public void onShipment(OrderDetails order){
        //TODO Perform operation to add shipment details
        stateMachine.sendEvent(OrderEvent.ON_SHIPMENT);
    }

}
