package com.mirim.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {
    @Around("execution(* com.mirim.board..*Service.*(..))")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        try {
            //진짜 메서드 실행
            //핵심 관심사 실행
            return joinPoint.proceed();
        }finally {
            long time = System.currentTimeMillis() - start;
            System.out.println("[실행 시간] " + joinPoint.getSignature().toShortString() + " " + time + "ms");
        }
    }
}
