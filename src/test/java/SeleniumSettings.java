import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * В классе SeleniumSettings осуществляется настройка веб-драйвера
 */

public class SeleniumSettings {

    protected static WebDriver driver;

    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }

}
