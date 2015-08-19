package com.service.core;

import com.entity.core.Announce;
import com.entity.core.ProBack;

/**
 * Created by dqf on 2015/8/19.
 */
public interface EcPushLogService{
    /**
     * 公告通知
     */
    public void sendAnnouncementNotification(Announce announce);

    /**
     * 项目反馈
     * @param proback
     */
    public void sendProbackNotification(ProBack proback);
}