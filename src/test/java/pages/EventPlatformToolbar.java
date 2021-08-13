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

/**
 * В классе EventPlatformToolbar реализована логика работы с навигационной панелью сайта
 */
public class EventPlatformToolbar {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By eventsLink = By.xpath("//ul[@class = 'evnt-navigation navbar-nav']//a[@href= '/events']");
    private final By talksLibraryLink = By.xpath("//ul[@class = 'evnt-navigation navbar-nav']//a[@href= '/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']");
    private final By upcomingEventsTab = By.xpath("//span[contains(text(),'Upcoming events')]");

    /**
     * В методе EventPlatformToolbar() происходит инициализация веб-драйвера, а также настравается ожидание
     *
     * @param driver веб-драйвер
     */
    public EventPlatformToolbar(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * В методе goToEventsDigitalPlatform() осуществляется переход на сайт
     */
    public void goToEventsDigitalPlatform() {
        driver.get(cfg.url());
        logger.info("Перешли на сайт {}", cfg.url());
    }

    /**
     * В методе goToEvents() осуществляется переход на вкладку Events
     */
    public void goToEvents() {
        wait.until(ExpectedConditions.elementToBeClickable(eventsLink));
        driver.findElement(eventsLink).click();
        wait.until(ExpectedConditions.elementToBeClickable(upcomingEventsTab));
        logger.info("Перешли на вкладку Events");
    }

    /**
     * В методе goToTalksLibrary() осуществляется переход на вкладку Talks Library
     */
    public void goToTalksLibrary() {
        wait.until(ExpectedConditions.elementToBeClickable(talksLibraryLink));
        driver.findElement(talksLibraryLink).click();
        logger.info("Перешли на вкладку Talks Library");
    }
}
