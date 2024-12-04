package com.purplemango.gms.messaging;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Slf4j
@Component
@Qualifier("KafkaTemplate")
public class MessageQueueServiceImpl implements MessageQueueService {

    @Autowired
    @Qualifier("GenericKafka")
    KafkaTemplate kafkaTemplate;

    @Override
    public void processMessage(String topic, Object object) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, UUID.randomUUID().toString(), object);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> t) {
                log.info("Message [{}] delivered with offset {}",object, t.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable thrwbl) {
                throw new TimeoutException("Failed to process request to kafka server. ", thrwbl);
            }
        });
    }
}
