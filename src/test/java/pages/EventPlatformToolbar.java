package pages;

import com.example.tests.ApplicationTests;
import com.example.tests.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventPlatformToolbar {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public EventPlatformToolbar(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }


    private final  By eventsLink = By.xpath("//ul[@class = 'evnt-navigation navbar-nav']//a[@href= '/events']");
    private final  By talksLibraryLink = By.xpath("//ul[@class = 'evnt-navigation navbar-nav']//a[@href= '/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']");


    public void goToEventsDigitalPlatform(){
        driver.get(cfg.url());
        logger.info("Перешли на сайт " + cfg.url());
    }

    public void goToEvents(){
        wait.until(ExpectedConditions.elementToBeClickable(eventsLink));
        driver.findElement(eventsLink).click();
        logger.info("Перешли на вкладку Events");
    }

    public void goToTalksLibrary(){
        wait.until(ExpectedConditions.elementToBeClickable(talksLibraryLink));
        driver.findElement(talksLibraryLink).click();
        logger.info("Перешли на вкладку Talks Library");
    }
}
