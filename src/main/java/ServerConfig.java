import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources(value = "classpath:config.properties")
public interface ServerConfig extends Config {
    String url();
    String name();
    String surname();
    int countMethods();
}
