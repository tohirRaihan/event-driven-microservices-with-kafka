package com.tohir.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.tohir.orderservice.utils.constant.KafkaConstants;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic topicLogs() {
        return TopicBuilder.name(KafkaConstants.TOPIC_ORDERS).partitions(3).replicas(1).build();
    }
}
