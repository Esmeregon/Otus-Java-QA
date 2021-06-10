package com.example.auto;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
class AutoApplicationTests {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(AutoApplicationTests.class);

    @Test
    void checkingFamilyCar() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Car carForFamily = context.getBean("classicCar", Car.class);
        Assert.isTrue(carForFamily.readyForSale().equals(cfg.messageForClassicCar()), "Wrong message: \"" +  carForFamily.readyForSale() + "\" instead \"" + cfg.messageForClassicCar() + "\"");
        logger.info("Checked classicCar");
    }

    @Test
    void checkingSportCar() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Car carForYoung = context.getBean("sportCar", Car.class);
        Assert.isTrue(carForYoung.readyForSale().equals(cfg.messageForSportCar()),  "Wrong message: \"" +  carForYoung.readyForSale() + "\" instead \"" + cfg.messageForSportCar() + "\"");
        logger.info("Checked sportCar");
    }

    @Test
    void checkingBusinessCar() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Car carForEntrepreneur = context.getBean("businessCar", Car.class);
        Assert.isTrue(carForEntrepreneur.readyForSale().equals(cfg.messageForBusinessCar()),  "Wrong message: \"" +  carForEntrepreneur.readyForSale() + "\" instead \"" + cfg.messageForBusinessCar() + "\"");
        logger.info("Checked businessCar");
    }

    @Test
    void checkingCommercialCar() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Car carForCargo = context.getBean("commercialCar", Car.class);
        Assert.isTrue(carForCargo.readyForSale().equals(cfg.messageForCommercialCar()),  "Wrong message: \"" +  carForCargo.readyForSale() + "\" instead \"" + cfg.messageForCommercialCar() + "\"");
        logger.info("Checked commercialCar");
    }

}
