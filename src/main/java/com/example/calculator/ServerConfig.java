package com.example.calculator;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources("classpath:config.properties")
public interface ServerConfig extends Config {
    String userFirstName();
    String userLastName();
    String resourceName();
    Integer resourceYear();
}