package utils;

import com.example.tests.Browsers;
import com.example.tests.ServerConfig;
import com.example.tests.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class SeleniumSettings {

    protected static WebDriver driver;
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


    @BeforeEach
    public void startUp(){
        driver = WebDriverFactory.createDriver(Browsers.valueOf(cfg.browser().toUpperCase(Locale.ROOT)));
        assert driver != null;
        driver.manage().window().maximize();
    }

    @AfterEach
    public void end(){
        if (driver!=null)
            driver.quit();
    }

}