package com.example.auto;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources("classpath:config.properties")
public interface ServerConfig extends Config {
    String messageForSportCar();
    String messageForClassicCar();
    String messageForBusinessCar();
    String messageForCommercialCar();
}