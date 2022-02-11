package com.example.socstudy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAop {

    @After(value = "within(com.example.socstudy.main.blizzard..*)")
    public void logAop(JoinPoint joinPoint) {

    }
}
