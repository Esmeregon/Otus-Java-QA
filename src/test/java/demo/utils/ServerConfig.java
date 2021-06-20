package demo.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources(value = "classpath:config.properties")
public interface ServerConfig extends Config {
    String browser();
    String url();
    String email();
    String password();
}