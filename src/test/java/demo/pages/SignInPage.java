package demo.pages;

import demo.RunCucumberTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    private final Logger logger = LogManager.getLogger(RunCucumberTest.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logInField = By.xpath("//input[@type= 'text'][@placeholder= 'Электронная почта']");
    private final By passwordField = By.xpath("//input[@name = 'password']");
    private final By submitButton = By.xpath("//button[@class = 'new-button new-button_full new-button_blue new-button_md']");
    private final By errorAuthorizationMessage = By.xpath("//div[@class = 'new-input-error new-input-error_top new-input-error_form js-text']");

    /**
     * В методе SignInPage происходит инициализация веб-драйвера,
     * а также настравается ожидание
     * @param driver веб-драйвер
     * */
    public SignInPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    /**
     * В методе logIn() происходит заполнение полей ввода и дальнейшая авторизация на сайте
     */
    public void logIn(String email, String password){
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        driver.findElement(logInField).clear();
        driver.findElement(logInField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
        logger.info("Прошли авторизацию на сайте");
    }

    /**
     * В методе getErrorAuthorizationMessage() происходит поиск сообщения об ошибки авторизации
     * @return текст ошибки
     */
    public String getErrorAuthorizationMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorAuthorizationMessage));
        logger.info("Получили сообщение об ошибке авторизации");
        return driver.findElement(errorAuthorizationMessage).getText();
    }
}