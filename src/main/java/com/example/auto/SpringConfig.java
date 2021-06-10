package com.example.auto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.auto")
public class SpringConfig {

    @Bean
    public ClassicConfiguration classicConfiguration() {
        return new ClassicConfiguration();
    }

    @Bean
    public SportConfiguration sportConfiguration() {
        return new SportConfiguration();
    }

    @Bean
    public BusinessConfiguration businessConfiguration() {
        return new BusinessConfiguration();
    }

    @Bean
    public CommercialConfiguration commercialConfiguration() {
        return new CommercialConfiguration();
    }

    @Bean Car commercialCar() {
        return new Car(commercialConfiguration());
    }

    @Bean Car businessCar() {
        return new Car(businessConfiguration());
    }

    @Bean Car classicCar() {
        return new Car(classicConfiguration());
    }

    @Bean Car sportCar() {
        return new Car(sportConfiguration());
    }
}
