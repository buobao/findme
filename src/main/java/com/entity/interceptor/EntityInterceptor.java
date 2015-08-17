package com.entity.interceptor;

/**
 * Created by dqf on 2015/8/17.
 */

import com.utils.Identities;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class EntityInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 7319416231145791577L;
    private static final String CREATE_DATE = "createDate";
    private static final String MODIFY_DATE = "modifyDate";
    private static final String TIME_STAMP = "timestamp";

    public EntityInterceptor() {
    }

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        Date createDate = new Date();

        for(int i = 0; i < propertyNames.length; ++i) {
            if("createDate".equals(propertyNames[i])) {
                if(state[i] == null) {
                    state[i] = createDate;
                }
            } else if("modifyDate".equals(propertyNames[i])) {
                state[i] = createDate;
            } else if("timestamp".equals(propertyNames[i]) && state[i] == null) {
                String timestamp = Identities.uuid2();
                state[i] = timestamp;
            }
        }

        return true;
    }

    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        Date updateDate = new Date();

        for(int i = 0; i < propertyNames.length; ++i) {
            if("modifyDate".equals(propertyNames[i])) {
                currentState[i] = updateDate;
            }
        }

        return true;
    }
}
