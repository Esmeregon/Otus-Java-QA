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



    private final By eventTitle = By.xpath("//div[@class = 'evnt-content-text edit-wrapper hero']//h1");

    private final By registrationButton = By.xpath("//button[@id = 'no_reg_5583']");


    public void checkingEventDetail() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        Assertions.assertNotNull(driver.findElement(registrationButton).getText());
        logger.info("Проверили содержимое страницы");
    }

}
