package com.example.spring_state_machine;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

/**
 * Guards — Guards ensure that all the terms and conditions are being satisfied to enter any specific state in the state machine.
 * Guard can be configured by implementing Guard<S,E> interface and overriding evaluate method as given below.
 */
public class RefundInitiateGuard implements Guard<OrderState,OrderEvent> {

    @Override
    public boolean evaluate(StateContext<OrderState, OrderEvent> stateContext) {
        // TODO Check the order detail and order details that customer should not have selected pay on delivery as payment option
        return true;
    }
}
