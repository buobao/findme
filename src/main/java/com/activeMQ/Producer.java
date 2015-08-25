package com.activeMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by dqf on 2015/8/12.
 */
@Component
public class Producer implements CommandLineRunner {
    protected static Logger LOG = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    @Override
    public void run(String... strings) throws Exception {
        //send("Sample message");
        LOG.info("Message is send......");
    }

    public void send(String msg){
        this.jmsMessagingTemplate.convertAndSend(queue, msg);
    }
}
