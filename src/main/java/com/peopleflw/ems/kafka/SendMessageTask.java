package com.peopleflw.ems.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@Component
public class SendMessageTask {
    private final Logger logger = LoggerFactory.getLogger(SendMessageTask.class);

    private final Producer producer;

    public SendMessageTask(Producer producer) {
        this.producer = producer;
    }

    // run every 3 sec

    public void send(String jsonMessage) {

        ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage("INPUT_DATA",
                "IN_KEY", jsonMessage);

        SendResult<String, String> result;
        try {
            result = listenableFuture.get();
       
        logger.info(String.format("Produced:\ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d", result.getRecordMetadata().topic(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().partition(), result.getRecordMetadata().serializedValueSize()));
    
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage(), e);
            }
    }
}