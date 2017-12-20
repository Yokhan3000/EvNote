package com.epam.evnote.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Mikhail Chtetsov on 19/12/2017.
 */
@Component
@Aspect
public class ServiceMonitor {
  private static boolean ON = true;

  public static boolean isON() {
    return ON;
  }



  public static void setON(boolean ON) {
    ServiceMonitor.ON = ON;
  }

  @Pointcut("execution(* com.epam.evnote.service.impl..*(..))")
  public static boolean services() {
    return ON;
  }

  @Around("services()")
  public Object getTime(ProceedingJoinPoint joinpoint) {
      Object proceed = null;
    if(ON) {
      try {
        String methodName = "Service " + joinpoint.getSignature().getName();
        System.err.println("====================================");
        System.err.println(methodName + " start working.");
        long start = System.currentTimeMillis();
        proceed = joinpoint.proceed();
        long finish = System.currentTimeMillis();
        System.err.println(methodName + " was working " + (finish - start) + "millis");
        System.err.println("==============================");
      } catch (Throwable t) {
        t.printStackTrace();
      }
    } else {
      try {
        proceed = joinpoint.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return proceed;
  }
}
