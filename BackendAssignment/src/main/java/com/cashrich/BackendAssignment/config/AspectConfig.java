package com.cashrich.BackendAssignment.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
    Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    @Around("execution(* com.cashrich.BackendAssignment..*(..))")
    public Object aroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Method execution started for method " + proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName() + "." + proceedingJoinPoint.getSignature().getName() + "(..)");
        try {
            Object obj = proceedingJoinPoint.proceed();
            logger.info("Method execution completed for method " + proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName() + "." + proceedingJoinPoint.getSignature().getName() + "(..)");
            return obj;
        } catch (Exception exception) {
            logger.info("Exception occurred while executing method " + proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName() + "." + proceedingJoinPoint.getSignature().getName() + "(..)");
            throw exception;
        }
    }
}
