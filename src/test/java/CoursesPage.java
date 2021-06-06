import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * В классе CoursesPage реализована логика заполниения персональных данных пользователя на странице личного кабинета,
 * а также происходит добавление дополнительных контактных данных в List для дальнейшей обработки
 */

public class CoursesPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(Tests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By menu = By.xpath("//div[@class='header2-menu__item-wrapper header2-menu__item-wrapper__username']");
    private final By accountLink = By.xpath("//a[@href='/lk/biography/personal/']");


/**
 * В методе CoursesPage происходит инициализация веб-драйвера,
 * а также настравается ожидание
 * @param driver веб-драйвер
 * */
    public CoursesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

/**
 * В методе goToOtus() осуществляется переход на сайт
 */
    public void goToOtus(){
        driver.get(cfg.url());
        logger.info("Перешли на сайт " + cfg.url());
    }

/**
 * В методе accountLink() осуществляется переход на страницу личного кабинета
 */
    public void goToAccountSettings(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menu));
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(menu);
        action.moveToElement(we).moveToElement(driver.findElement(accountLink)).click().build().perform();
        logger.info("Перешли в Личный кабинет");
    }

}
