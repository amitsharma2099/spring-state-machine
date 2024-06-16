package com.example.spring_state_machine;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * State Machine Listener — Using StateMachineListener, we can either implement all callback methods which contains stub method
 * implementations and choose which ones to override as given below
 */
public class OrderStateMachineListener implements StateMachineListener<OrderState, OrderEvent> {
//public class OrderStateMachineListener extends StateMachineListenerAdapter<OrderState, OrderEvent> {

    @Override
    public void stateChanged(State<OrderState, OrderEvent> state, State<OrderState, OrderEvent> state1) {
        System.out.println("State changed from " + state + " to " + state1);
    }

    @Override
    public void stateEntered(State<OrderState, OrderEvent> state) {

    }

    @Override
    public void stateExited(State<OrderState, OrderEvent> state) {

    }

    @Override
    public void eventNotAccepted(Message<OrderEvent> message) {

    }

    @Override
    public void transition(Transition<OrderState, OrderEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<OrderState, OrderEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<OrderState, OrderEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<OrderState, OrderEvent> stateMachine) {

    }

    @Override
    public void stateMachineStopped(StateMachine<OrderState, OrderEvent> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<OrderState, OrderEvent> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<OrderState, OrderEvent> stateContext) {

    }
}
