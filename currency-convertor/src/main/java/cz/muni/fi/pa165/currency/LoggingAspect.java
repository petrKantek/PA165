package cz.muni.fi.pa165.currency;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Named;

/**
 * Class
 *
 * @author Petr Kantek
 */
@Named
@Aspect
public class LoggingAspect {

    @Around("execution(public * *(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Calling method: "
                + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("Method finished: "
                + joinPoint.getSignature());

        return result;
    }

}
