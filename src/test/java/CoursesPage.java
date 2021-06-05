import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CoursesPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(Tests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By accountLink = By.xpath("//a[@href='/learning/']");


    public CoursesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    public void goToOtus(){
        driver.get(cfg.url());
        logger.info("Перешли на сайт " + cfg.url());
    }

    public void accountLink(){
        wait.until(ExpectedConditions.elementToBeClickable(accountLink));
        driver.findElement(accountLink).click();
        logger.info("Перешли в Личный кабинет");
    }


}
