package com.example.tests;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {
    /*
    String urlOtus();
    String urlTele2();
*/

    String browser();
    String url();

    String location();
}
