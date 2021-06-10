package com.example.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Car carForFamily = context.getBean("classicCar", Car.class);
        Car carForYoung = context.getBean("sportCar", Car.class);
        Car carForEntrepreneur = context.getBean("businessCar", Car.class);
        Car carForCargo = context.getBean("commercialCar", Car.class);

        System.out.println(carForFamily.readyForSale());
        System.out.println(carForYoung.readyForSale());
        System.out.println(carForEntrepreneur.readyForSale());
        System.out.println(carForCargo.readyForSale());
    }
}
