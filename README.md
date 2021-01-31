# soda-machine

mvn spring-boot:run

## Description

My solution for a small coding assignment

## Assignment Instructions

Today people are using Java in real devices, like soda machines.  That’s right, soda machines have gone high tech; the major manufacturers have found that by putting CPUs into their machines, they can increases sales, monitor inventory over the network and measure customer satisfaction more accurately.

But these manufacturers are soda machine experts, not software developers and need some help.

Here’s the way we think the soda machine controller needs to work.  We’re hoping that you can implement this in Java for us. We may be adding more behavior in the future, so you need to keep the design as flexible and maintainable as possible.

[image here]

As depicted in the diagram above the following actions, behaviors and state transitions can happen.

States
1. Sold out
2. No quarter
3. Has quarter
4. Sold

Actions
1. Insert a quarter
2. Remove a quarter
3. Push the desired soda button
4. Dispense the soda

Because this is a high tech soda machine the following functionality is also required.
Simple modern GUI allowing you to purchase the soda and perform the actions listed above
Store each soda purchase so the information can be reviewed at a later date, i.e. reporting.
Retrieve the soda count from the microcontroller service that manages the current inventory
Support the possible addition of new functionality such as giveaways or contests.

* There is no right or wrong answer to the problem. You may be asked to justify or explain various design decisions regarding the UI, business logic, model, persistence, dependency management, modularity or other aspects of the code. Incorporating design patterns where appropriate is welcomed. 
