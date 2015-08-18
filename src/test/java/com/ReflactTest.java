package com;

import com.entity.base.Entity;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by dqf on 2015/8/18.
 */
public class ReflactTest<T extends Entity, PK extends Serializable> {
    @Test
    public void InfoTest(){
        Class entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType){
            Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();
            entityClass = (Class)parameterizedType[0];
        }
        System.out.println(entityClass);
        System.out.println("name:"+ReflactTest.class.getSimpleName());
    }
}
