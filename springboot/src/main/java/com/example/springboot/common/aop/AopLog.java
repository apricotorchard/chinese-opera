package com.example.springboot.common.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AopLog {
    @Around("execution(* com.example.springboot.controller..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 获取方法名和参数
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("Method {} is called with arguments: {}", methodName, args);

        Object result;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            // 记录返回值
            log.info("Method {} completed with return value: {}", methodName, result);
        } catch (Throwable throwable) {
            // 捕获异常并记录
            log.error("Method {} threw exception: {}", methodName, throwable.getMessage());
            throw throwable; // 继续抛出异常
        }

        // 记录结束时间并计算耗时
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("Method {} executed in {} ms", methodName, elapsedTime);

        // 返回结果
        return result;
    }
}
