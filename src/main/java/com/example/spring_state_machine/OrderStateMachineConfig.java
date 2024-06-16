package com.example.spring_state_machine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * Key Concepts of Spring Statemachine:
 * State: A condition or situation of an object at any given time.
 * Event: An occurrence that triggers a state transition.
 * Transition: The movement from one state to another.
 * Guard: A condition that must be true for a transition to occur.
 * Action: A piece of code executed as a result of a transition.
 */

@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.ORDERED)
                .states(EnumSet.allOf(OrderState.class))
                .stateEntry(OrderState.DELIVERED, entryAction())
                .state(OrderState.DELIVERED, executeAction())
                .stateExit(OrderState.DELIVERED, exitAction());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderState.ORDERED).event(OrderEvent.ON_SHIPMENT).target(OrderState.SHIPPED)
                .action(new OnShipmentAction())
                .and().withExternal()
                .source(OrderState.ORDERED).event(OrderEvent.ON_CANCEL).target(OrderState.CANCELLED)
                .and().withExternal()
                .source(OrderState.SHIPPED).event(OrderEvent.ON_DELIVERY).target(OrderState.OUT_FOR_DELIVERY)
                .and().withExternal()
                .source(OrderState.SHIPPED).event(OrderEvent.ON_CANCEL).target(OrderState.CANCELLED)
                .and().withExternal()
                .source(OrderState.OUT_FOR_DELIVERY).event(OrderEvent.ON_HANDOVER).target(OrderState.DELIVERED)
                .and().withExternal()
                .source(OrderState.DELIVERED).event(OrderEvent.ON_RETURN).target(OrderState.RETURN)
                .and().withExternal()
                .source(OrderState.RETURN).event(OrderEvent.ITEM_PICKUP).target(OrderState.PICKUP)
                .and().withExternal()
                .source(OrderState.PICKUP).event(OrderEvent.INITIATE_REFUND).target(OrderState.REFUND_INITIATED)
                .guard(new RefundInitiateGuard())
                .and().withExternal()
                .source(OrderState.PICKUP).event(OrderEvent.ON_EXCHANGE).target(OrderState.EXCHANGE)
                .and().withExternal()
                .source(OrderState.REFUND_INITIATED).event(OrderEvent.ON_PAYMENT).target(OrderState.REFUND_COMPLETED)
                .and().withExternal()
                .source(OrderState.EXCHANGE).event(OrderEvent.VALIDATE_ITEM).target(OrderState.EXCHANGE_COMPLETED)
                .and().withExternal()
                .source(OrderState.EXCHANGE_COMPLETED).event(OrderEvent.PROCESS_NEW_ITEM).target(OrderState.ORDERED);
    }

    @Bean
    public Action<OrderState, OrderEvent> entryAction() {
        return ctx -> System.out.println(
                "Entry " + ctx.getTarget().getId());
    }

    @Bean
    public Action<OrderState, OrderEvent> executeAction() {
        return ctx ->
                System.out.println("Execute " + ctx.getTarget().getId());
    }

    @Bean
    public Action<OrderState, OrderEvent> exitAction() {
        return ctx -> System.out.println(
                "Exit " + ctx.getSource().getId() + " -> " + ctx.getTarget().getId());
    }
}
