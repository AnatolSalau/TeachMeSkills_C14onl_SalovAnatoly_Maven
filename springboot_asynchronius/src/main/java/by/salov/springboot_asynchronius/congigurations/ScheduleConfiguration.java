package by.salov.springboot_asynchronius.congigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Turn on scheduling in configuration
 */
@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    @Bean
    public ThreadPoolTaskScheduler threadPoolExecutor() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        taskScheduler.setThreadNamePrefix("Custom schedule executor - ");
        return  taskScheduler;
    }
}
