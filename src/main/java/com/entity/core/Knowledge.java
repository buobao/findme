package com.entity.core;

import com.entity.base.NameEntity;
import com.entity.sys.Dict;

import javax.persistence.*;

/**
 * Created by dqf on 2015/8/18.
 */
@Entity
@Table(name="ec_knowledge")
public class Knowledge extends NameEntity {
    /**
     * 知识大类
     */
    private Dict superType;
    /**
     * 知识小类
     */
    private Dict subType;
    /**
     * 知识内容
     */
    private String context;
    /**
     * 知识内容
     */
    private String contextHtml;
    /**
     * 文件上传
     */
    private String knowledgeId;

    @ManyToOne(fetch = FetchType.LAZY)
    public Dict getSuperType() {
        return superType;
    }


    public void setSuperType(Dict superType) {
        this.superType = superType;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    public Dict getSubType() {
        return subType;
    }

    public void setSubType(Dict subType) {
        this.subType = subType;
    }

    @Column(length = 65535)
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Column(length = 65535)
    public String getContextHtml() {
        return contextHtml;
    }

    public void setContextHtml(String contextHtml) {
        this.contextHtml = contextHtml;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }
}

