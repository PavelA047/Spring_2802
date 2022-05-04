package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        ConsumerService consumerService = context.getBean("consumerService", ConsumerService.class);

        System.out.println(consumerService.getProductListByConsumerId(15L));
        System.out.println(consumerService.getConsumerListByProductId(108L));
    }
}
