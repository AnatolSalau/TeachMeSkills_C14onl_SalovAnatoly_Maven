package by.salov.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//Benchmark aspects
@Component
@Aspect
public class BenchmarkAspect {
        //Указываем путь до метода к которому будем применять аспект
        //@Pointcut("execution(public void by.salov.repository.UserRepository.save(*))")

    //Указываем путь до всех публичных методов в классе UserRepository
/*    @Pointcut("execution(* by.salov.repository.UserRepository.*(..))")
        public void benchSevices() {}*/

    //Аспект будет работать только над выделенными аннотацией методами
    @Pointcut("@annotation(by.salov.annotations.BenchMark)")
    public void testBenchPointCut() {}

    //Указываем где в точке среза должна выполняться наша логика
    //JoinPoint joinPoint - хранит информацию о том где этот аспект выполняется
/*    @Before("benchSevices()")
    public void startBench(JoinPoint joinPoint) {
        System.out.println("--------start bench---------");
        //joinPoint.getArgs();

    }
    @After("benchSevices()")
    public void endBench() {
        System.out.println("--------end bench---------");
    }*/

    //ProceedingJoinPoint - позволяет запускать обернутый метод в необходимый момент
    @Around("testBenchPointCut()")
    public void bench(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("--------start bench---------");
        long start = System.currentTimeMillis();
        //запускаем обернутый метод
        proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("end bench");
    }
}
