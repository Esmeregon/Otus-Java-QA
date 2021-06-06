import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Locale;


/**
 * В классе SeleniumSettings осуществляется настройка веб-драйвера
 */

public class SeleniumSettings {

    protected static WebDriver driver;
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


    @Before
    public void startUp(){
        driver = WebDriverFactory.createDriver(Browsers.valueOf(cfg.browser().toUpperCase(Locale.ROOT)));
    }

    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }

}
