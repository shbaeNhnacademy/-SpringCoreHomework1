package com.nhnacademy.edu.springframework.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class TimeLogging {
    @Pointcut("execution(* com.nhnacademy.edu.springframework.project.service.*.*(..))")
    private void serviceMethod() {
    }

    @Around("serviceMethod()")
    public Object checkOperatingTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch("Time Tracker");
        try {
            stopWatch.start();

            Object proceed = pjp.proceed();
            return proceed;

        }finally {
            stopWatch.stop();
            System.out.println("[" + pjp.getTarget().getClass().getSimpleName() + "]." +
                    "[" + pjp.getSignature().getName() + "]" +
                    "   [" + stopWatch.getTotalTimeMillis() + "]ms");
        }

    }

}
