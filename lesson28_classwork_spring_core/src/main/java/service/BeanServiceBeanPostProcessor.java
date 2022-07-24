package service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//Create custom BeanPostProcessor
public class BeanServiceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        System.out.println(bean.toString());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        System.out.println(bean.toString());
        return bean;
    }
}
