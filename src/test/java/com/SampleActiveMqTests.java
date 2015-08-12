package com;

import com.activeMQ.Producer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertTrue;

/**
 * Created by dqf on 2015/8/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class SampleActiveMqTests {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Autowired
    private Producer producer;

    @Test
    public void sendSimpleMessage() throws InterruptedException {
        this.producer.send("Test message");
        Thread.sleep(1000L);
        assertTrue(this.outputCapture.toString().contains("Test message"));
    }
}
