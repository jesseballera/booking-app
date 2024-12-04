package com.purplemango.gms.messaging;

public interface MessageQueueService {
    void processMessage(String topic, Object object);
}
