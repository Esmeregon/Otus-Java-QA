import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

import java.util.List;

@Sources(value = "classpath:config.properties")
public interface ServerConfig extends Config {
    String url();
    List<String> brands();
    String sortingMethod();
}
