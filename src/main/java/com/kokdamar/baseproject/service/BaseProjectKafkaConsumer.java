package com.kokdamar.baseproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BaseProjectKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(BaseProjectKafkaConsumer.class);
    private static final String TOPIC = "topic_baseproject";

    @KafkaListener(topics = "topic_baseproject", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
