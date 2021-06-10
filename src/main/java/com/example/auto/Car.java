package com.example.auto;

public class Car {

    private final Configurable configuration;

    public Car(Configurable configuration) {
        this.configuration = configuration;
    }

    public String readyForSale(){
        return "Car Ready in: " + configuration.makeConfiguration();
    }

}
