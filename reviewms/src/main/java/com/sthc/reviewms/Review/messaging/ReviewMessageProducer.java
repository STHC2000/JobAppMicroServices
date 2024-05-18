package com.sthc.reviewms.Review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
