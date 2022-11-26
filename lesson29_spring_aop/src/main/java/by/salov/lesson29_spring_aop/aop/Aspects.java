package by.salov.lesson29_spring_aop.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspects {

    private static Logger logger = LoggerFactory.getLogger(Aspects.class);

    @Before("@annotation(by.salov.lesson29_spring_aop.annotations.LogBefore)")
    public void logBefore(JoinPoint joinPoint) {
        logger.error("Log before method : " + joinPoint.getSignature().getName() + ".\n");
        System.out.println("Log before");
    }

    @AfterReturning(pointcut = "@annotation(by.salov.lesson29_spring_aop.annotations.LogAfter)")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Log after");
    }

    @AfterThrowing(pointcut = "@annotation(by.salov.lesson29_spring_aop.annotations.LogAfterThrowing)", throwing = "ex")
    public void logAfterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Log after throwing " + ex);
    }

    @Around("@annotation(by.salov.lesson29_spring_aop.annotations.LogAround)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Log around execution. - Roles: ");
        Object result = joinPoint.proceed();
        return result;
    }
    @Around("@annotation(by.salov.lesson29_spring_aop.annotations.BenchExecutionAround)")
    public Object benchExecutionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String packageName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        System.out.println("Package name : " + packageName);
        System.out.println("Start bench of : " + methodName + ":" + start);

        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();

        System.out.println("End bench of : " + methodName + ":" + end);
        System.out.println("Duration: " + (end - start));

        return proceed;
    }

}
