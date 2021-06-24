import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources(value = "classpath:config.properties")
public interface ServerConfig extends Config {
    String email();
    Long userStatus();
    String firstName();
    Long id();
    String lastName();
    String password();
    String phone();
    String username();

    int code();
    String type();
}