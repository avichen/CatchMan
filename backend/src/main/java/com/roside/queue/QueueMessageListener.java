package com.roside.queue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Created by Eric on 2017-01-27.
 */
public class QueueMessageListener implements MessageListener {

    protected org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message, byte[] bytes) {
        logger.info("This is the received message: {}", message);
    }
}
