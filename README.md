# Spring boot, Active MQ (Artemis MQ) Embedded and Apache Camel

Example of usage Embedded Active MQ Artemis broker with Apache Camel.

Project contains example of configuration and basic operations with Embedded Artemis and Camel.
Also there are some tests. 

## How to run
```
mvn clean package spring-boot:run
```

## How to see 

After app is running go to http://localhost:7111/ws-app/v1/send

You should receive UUID. What was happening:
* Message was created with `UUID.randomUUID()` and put to the queue1
* Camel routing read message from the queue1, log it and redirect to the queue2
* Message was read by `jmsTemplate` from the queue2 and returned
