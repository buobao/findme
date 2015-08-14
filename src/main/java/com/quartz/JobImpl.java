package com.quartz;


/**
 * Created by dqf on 2015/8/14.
 */
public class JobImpl implements Job{
    @Override
    public void evalExpire() {
        System.out.println("Quartz>>>test....");
    }

    @Override
    public void doProAttendDeal() {

    }
}
