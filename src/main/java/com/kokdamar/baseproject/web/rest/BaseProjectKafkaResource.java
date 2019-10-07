package com.kokdamar.baseproject.web.rest;

import com.kokdamar.baseproject.service.BaseProjectKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/base-project-kafka")
public class BaseProjectKafkaResource {

    private final Logger log = LoggerFactory.getLogger(BaseProjectKafkaResource.class);

    private BaseProjectKafkaProducer kafkaProducer;

    public BaseProjectKafkaResource(BaseProjectKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
