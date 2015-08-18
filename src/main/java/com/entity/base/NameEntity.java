package com.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by dqf on 2015/8/17.
 */
@MappedSuperclass
public class NameEntity extends BaseEntity {
    private static final long serialVersionUID = -8017227280344654760L;
    /**
     * 名称
     */
    private String name;
    /**
     * 拼音首字母
     */
    private String pinYinHead;
    /**
     * 拼音
     */
    private String pinYin;
    /**
     * 系统内置编号
     * */
    private String no;
    /**
     * 接口编号
     * */
    private String apino;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYinHead() {
        return pinYinHead;
    }

    public void setPinYinHead(String pinYinHead) {
        this.pinYinHead = pinYinHead;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getApino() {
        return apino;
    }

    public void setApino(String apino) {
        this.apino = apino;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
