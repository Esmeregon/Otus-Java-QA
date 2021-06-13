package com.example.calculator;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class CalculatorApplicationTests {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(CalculatorApplicationTests.class);
    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    @DisplayName("test sum")
    void test1(){
        assert calculator.sum(cfg.number1(), cfg.number2()).equals(cfg.sum());
        logger.info("Checked sum: " + cfg.number1() + " + " + cfg.number2() + " = " + cfg.sum());
    }

    @Test
    @DisplayName("test subtraction")
    void test2(){
        assert calculator.subtraction(cfg.number1(), cfg.number2()).equals(cfg.subtraction());
        logger.info("Checked subtraction: " + cfg.number1() + " - " + cfg.number2() + " = " + cfg.subtraction());
    }

    @Test
    @DisplayName("test multiply")
    void test3(){
        assert calculator.multiply(cfg.number1(), cfg.number2()).equals(cfg.multiply());
        logger.info("Checked multiply: " + cfg.number1() + " * " + cfg.number2() + " = " + cfg.multiply());
    }

    @Test
    @DisplayName("test division")
    void test4(){
        assert calculator.division(cfg.number1(), cfg.number2()).equals(cfg.division());
        logger.info("Checked division: " + cfg.number1() + " / " + cfg.number2() + " = " + cfg.division());
    }

    @Test
    @DisplayName("test square number")
    void test5(){
        assert calculator.squareNumber(cfg.number1()).equals(cfg.squareNumber1());
        logger.info("Checked square number: " + cfg.number1() + "²" + " = " + cfg.squareNumber1());
    }

    @Test
    @DisplayName("test square root")
    void test6(){
        Assertions.assertEquals(calculator.squareRoot(cfg.number1()), cfg.squareRootNumber1());
        logger.info("Checked square root: " + "√" + cfg.number1() + " = " + cfg.squareRootNumber1());
    }

}
