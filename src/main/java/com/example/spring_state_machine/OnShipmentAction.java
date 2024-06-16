package com.example.spring_state_machine;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * Actions — Actions are can use to interact and collaborate with a state machine. we can run actions at entering, exiting a state in a state machine.
 * Action can be configured by implementing Action<S,E> interface and overriding execute method as given below.
 */
public class OnShipmentAction implements Action<OrderState,OrderEvent> {

    @Override
    public void execute(StateContext<OrderState, OrderEvent> stateContext) {
        //TODO Send notification mail to the customer as item has been shipped
        System.out.println("Notification sent");
    }
}
