package pages;

import com.example.tests.ApplicationTests;
import com.example.tests.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventViewPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public EventViewPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    private final By eventLanguage = By.xpath("//div[@class= 'evnt-talk-details language evnt-now-past-talk']");
    private final By eventCategory = By.xpath("//label[contains(text(),'" + cfg.category() +"')]");
    private final By eventLocation = By.xpath("//span[contains(text(),'" + cfg.libraryLocation() + "')]");


    public void checkingEventDetail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventLanguage));
        Assertions.assertEquals(cfg.language(), driver.findElement(eventLanguage).getText());
        Assertions.assertEquals(cfg.category(), driver.findElement(eventCategory).getText());
        Assertions.assertTrue(driver.findElement(eventLocation).getText().contains(cfg.libraryLocation()));
        logger.info("Проверили содержимое страницы");
    }
}
