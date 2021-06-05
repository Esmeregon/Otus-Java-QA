import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * В классе SeleniumSettings осуществляется настройка веб-драйвера
 */

public class SeleniumSettings {

    protected static WebDriver driver;

    @Before
    public void startUp(){
        driver = WebDriverFactory.createDriver(Browsers.OPERA);
    }

    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }

}
