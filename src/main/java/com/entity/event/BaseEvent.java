package com.entity.event;

import com.entity.base.Entity;
import com.entity.base.MongoEntity;
import org.springframework.context.ApplicationEvent;

/**
 * Created by dqf on 2015/8/19.
 */
public abstract class BaseEvent extends ApplicationEvent {
    public BaseEvent(Entity entity) {
        super(entity);
    }

    public BaseEvent(MongoEntity entity) {
        super(entity);
    }
}