package com.tohir.emailservice.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tohir.basedomains.dto.OrderEvent;
import com.tohir.emailservice.utils.constant.KafkaConstants;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = KafkaConstants.TOPIC_ORDERS, groupId = "${spring.kafka.consumer.group-id}")
    public void onConsumed(ConsumerRecord<String, OrderEvent> consumerRecord) {
        String topic = consumerRecord.topic();
        OrderEvent event = consumerRecord.value();

        LOGGER.info("Order event received from topic '{}' in email service => {}", topic, event);
    }

}
