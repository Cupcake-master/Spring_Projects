package ru.bulat.resume_service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Logging for performing basic operations on the resume entity
 * @author Bulat Bilalov
 * @version v1.0
 */

@Component
@Aspect
@Log4j2
public class LogAspect {

    /**
     * Method of logging data output for ResumeController
     * @return Data returned by methods from ResumeController
     */

    @Around("execution(* ru.bulat.resume_service.controller.*.*(..))")
    public Object aroundServiceMethodExecution(ProceedingJoinPoint jp) throws Throwable {
        Object data = jp.proceed();
        log.info("Returned data: " + data);
        return data;
    }
}
