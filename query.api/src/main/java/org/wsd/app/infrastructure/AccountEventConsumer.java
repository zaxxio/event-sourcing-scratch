package org.wsd.app.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.wsd.app.events.AccountClosedEvent;
import org.wsd.app.events.AccountCreatedEvent;
import org.wsd.app.events.CashDepositedEvent;
import org.wsd.app.events.CashWithdrawnEvent;
import org.wsd.app.handler.EventHandler;

@Service
public class AccountEventConsumer implements EventConsumer {

    @Autowired
    private EventHandler eventHandler;

    @Override
    @KafkaListener(topics = {"AccountCreatedEvent"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AccountCreatedEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
    }

    @Override
    @KafkaListener(topics = {"CashDepositedEvent"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CashDepositedEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
        ;
    }

    @Override
    @KafkaListener(topics = {"CashWithdrawnEvent"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CashWithdrawnEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
        ;
    }

    @Override
    @KafkaListener(topics = {"AccountClosedEvent"}, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AccountClosedEvent event, Acknowledgment acknowledgment) {
        eventHandler.on(event);
        acknowledgment.acknowledge();
        ;
    }
}
