https://www.baeldung.com/spring-state-machine

Fork
Sometimes it becomes necessary to split the execution into multiple independent execution paths. This can be achieved using the fork functionality.

First, we need to designate a node as a fork node and create hierarchical regions into which the state machine will perform the split:

states
.withStates()
.initial("SI")
.fork("SFork")
.and()
.withStates()
.parent("SFork")
.initial("Sub1-1")
.end("Sub1-2")
.and()
.withStates()
.parent("SFork")
.initial("Sub2-1")
.end("Sub2-2");

Then define fork transition:

.withFork()
.source("SFork")
.target("Sub1-1")
.target("Sub2-1");

Join
The complement of the fork operation is the join. It allows us to set a state transitioning to which is dependent on completing some other states:

As with forking, we need to designate a join node in the state definition:

states
.withStates()
.join("SJoin")

Then in transitions, we define which states need to complete to enable our join state:

transitions
.withJoin()
.source("Sub1-2")
.source("Sub2-2")
.target("SJoin");

With this configuration, when both Sub1-2 and Sub2-2 are achieved, the state machine will transition to SJoin

State Machine from a Builder
StateMachineBuilder can be used to create a state machine without using Spring annotations or creating a Spring context:

StateMachineBuilder.Builder<String, String> builder
= StateMachineBuilder.builder();
builder.configureStates().withStates()
.initial("SI")
.state("S1")
.end("SF");

builder.configureTransitions()
.withExternal()
.source("SI").target("S1").event("E1")
.and().withExternal()
.source("S1").target("SF").event("E2");

StateMachine<String, String> machine = builder.build();

Hierarchical States
Hierarchical states can be configured by using multiple withStates() in conjunction with parent():

states
.withStates()
.initial("SI")
.state("SI")
.end("SF")
.and()
.withStates()
.parent("SI")
.initial("SUB1")
.state("SUB2")
.end("SUBEND");

This kind of setup allows the state machine to have multiple states, so a call to getState() will produce multiple IDs. For example, immediately after startup the following expression results in:

stateMachine.getState().getIds()
["SI", "SUB1"]


Junctions (Choices)
So far, we’ve created state transitions which were linear by nature. Not only is this rather uninteresting, but it also does not reflect real-life use-cases that a developer will be asked to implement either. The odds are conditional paths will need to be implemented, and Spring state machine’s junctions (or choices) allow us to do just that.

First, we need to mark a state a junction (choice) in the state definition:

states
.withStates()
.junction("SJ")

Then in the transitions, we define first/then/last options which correspond to an if-then-else structure:

.withJunction()
.source("SJ")
.first("high", highGuard())
.then("medium", mediumGuard())
.last("low")

first and then take a second argument which is a regular guard which will be invoked to find out which path to take:

@Bean
public Guard<String, String> mediumGuard() {
return ctx -> false;
}

@Bean
public Guard<String, String> highGuard() {
return ctx -> false;
}

Note that a transition does not stop at a junction node but will immediately execute defined guards and go to one of the designated routes.

In the example above, instructing state machine to transition to SJ will result in the actual state to become low as the both guards just return false.

A final note is that the API provides both junctions and choices. However, functionally they are identical in every aspect.