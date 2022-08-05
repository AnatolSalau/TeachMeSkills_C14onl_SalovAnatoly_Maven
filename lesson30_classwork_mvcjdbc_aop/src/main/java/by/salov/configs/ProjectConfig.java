package by.salov.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
//Включаем аннотации @Async - помечать асинхронные методы
@EnableAsync
//Включаем аннотации @Scheduled - помечать методы
// автоматически запускаемые через определенные промежутки времени
@EnableScheduling
public class ProjectConfig {
}
