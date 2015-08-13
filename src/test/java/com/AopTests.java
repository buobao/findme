package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.junit.Assert.assertTrue;

/**
 * Created by dqf on 2015/8/12.
 */
public class AopTests {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String profiles;

    @Before
    public void init(){
        this.profiles = System.getProperty("spring.profiles.active");
    }

    @After
    public void after(){
        if (this.profiles != null){
            System.setProperty("spring.profiles.active", this.profiles);
        } else{
            System.clearProperty("spring.profiles.active");
        }
    }

    @Test
    public void testDefaultSetting(){
        Application.main(new String[0]);
        String output = this.outputCapture.toString();
        assertTrue("Srong output: "+output,output.contains("Hello,Buobao!"));
    }

    @Test
    public void testCommandLineOverrides() throws Exception {
        Application.main(new String[] { "--name=Gordon" });
        String output = this.outputCapture.toString();
        assertTrue("Wrong output: " + output, output.contains("Hello Gordon"));
    }
}
