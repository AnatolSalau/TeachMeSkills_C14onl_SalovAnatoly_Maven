package by.salov.lesson29_spring_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class BenchMarkAspect {

    @Pointcut("execution(public UserMy by.salov.lesson29_spring_aop.dao.UserRepository.saveUser(*))")
    public void benchServices() {}

    @Before("benchServices()")
    public void startBench(){
        System.out.println("___________________________BENCH________________________");
    }

}
