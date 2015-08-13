package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by dqf on 2015/8/12.
 */
@Aspect
@Component
public class ServiceMonitor {
    protected static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ServiceMonitor.class);
    @AfterReturning("execution(* com..*Service.*(..))")
    public void logServiceAccess(JoinPoint joinPoint){
        LOG.info("AOP >>> Completed: "+joinPoint);
    }
}
