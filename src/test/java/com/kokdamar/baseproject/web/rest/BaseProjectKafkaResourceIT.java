package com.kokdamar.baseproject.web.rest;

import com.kokdamar.baseproject.BaseProjectApp;
import com.kokdamar.baseproject.config.TestSecurityConfiguration;
import com.kokdamar.baseproject.service.BaseProjectKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = {BaseProjectApp.class, TestSecurityConfiguration.class})
public class BaseProjectKafkaResourceIT {

    @Autowired
    private BaseProjectKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        BaseProjectKafkaResource kafkaResource = new BaseProjectKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/base-project-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
