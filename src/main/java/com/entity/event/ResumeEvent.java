package com.entity.event;

import com.entity.module.Resume;

/**
 * 履历的生成事件
 * */
public class ResumeEvent extends BaseEvent {
    public ResumeEvent(Resume resume) {
        super(resume);
    }
}
