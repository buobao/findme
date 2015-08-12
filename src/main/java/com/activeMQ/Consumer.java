package com.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by dqf on 2015/8/12.
 */
@Component
public class Consumer {
    protected static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Consumer.class);
    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text){
        LOG.info("Received Message:"+text);
    }
}
