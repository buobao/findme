package com.entity.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dqf on 2015/8/17.
 */
@MappedSuperclass
public class Entity implements Serializable{
    private static final long serialVersionUID=-2755329278196422648L;
    protected String id;
    protected Date createDate;
    protected Date modifyDate;
    protected String timestamp;


    public Entity() {
    }

    @Id
    @Column(
            length = 32,
            nullable = false
    )
    @GeneratedValue(
            generator = "uuid"
    )
    @GenericGenerator(
            name = "uuid",
            strategy = "uuid"
    )
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(
            updatable = false
    )
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int hashCode() {
        return this.id == null?System.identityHashCode(this):this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass().getPackage() != obj.getClass().getPackage()) {
            return false;
        } else {
            Entity other = (Entity)obj;
            if(this.id == null) {
                if(other.getId() != null) {
                    return false;
                }
            } else if(!this.id.equals(other.getId())) {
                return false;
            }

            return true;
        }
    }

    @Column(
            nullable = false,
            unique = true
    )
    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
