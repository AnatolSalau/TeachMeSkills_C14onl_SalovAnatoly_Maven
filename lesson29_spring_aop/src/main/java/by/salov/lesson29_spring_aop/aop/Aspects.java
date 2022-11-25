package by.salov.lesson29_spring_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspects {

    @Before("@annotation(by.salov.lesson29_spring_aop.annotations.LogBefore)")
    public void checkSomethingBefore(JoinPoint joinPoint) {
        System.out.println("Log before");
    }

    @AfterReturning(pointcut = "@annotation(by.salov.lesson29_spring_aop.annotations.LogAfter)")
    public void checkSomethingAfter(JoinPoint joinPoint) {
        System.out.println("Log after");
    }

    @AfterThrowing(pointcut = "@annotation(by.salov.lesson29_spring_aop.annotations.LogAfterThrowing)", throwing = "ex")
    public void checkSomethingAfterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Log after throwing " + ex);
    }

    @Around("@annotation(by.salov.lesson29_spring_aop.annotations.LogAround)")
    public Object checkSomethingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Log around execution. - Roles: ");
        Object result = joinPoint.proceed();
        return result;
    }
    @Around("@annotation(by.salov.lesson29_spring_aop.annotations.BenchExecutionAround)")
    public Object benchExecutionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Log around execution. - Roles: ");
        Object result = joinPoint.proceed();
        return result;
    }
}
