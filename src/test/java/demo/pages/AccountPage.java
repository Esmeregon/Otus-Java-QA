package demo.pages;

import demo.RunCucumberTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AccountPage {

    private final Logger logger = LogManager.getLogger(RunCucumberTest.class);
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By title = By.xpath("//h1[@class = 'title__text']");

    /**
     * В методе AccountPage происходит инициализация веб-драйвера,
     * а также настравается ожидание
     * @param driver веб-драйвер
     * */
    public AccountPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    public String checkedPageTitle(){
        logger.info("Проверили title страницы");
        return driver.findElement(title).getText();
    }

}
