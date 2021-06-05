import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    private final Logger logger = LogManager.getLogger(Tests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logInButton = By.xpath("//button[@data-modal-id = 'new-log-reg']");
    private final By logInMessage = By.xpath("//span[contains( text(),' Войдите в свой аккаунт')]");
    private final By logInField = By.xpath("//input[@type= 'text'][@placeholder= 'Электронная почта']");
    private final By passwordField = By.xpath("//input[@name = 'password']");
    private final By submitButton = By.xpath("//button[@class = 'new-button new-button_full new-button_blue new-button_md']");


    public SignInPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    public void logIn(String email, String password){
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        driver.findElement(logInButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(logInMessage));
        driver.findElement(logInField).clear();
        driver.findElement(logInField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
        logger.info("Прошли авторизацию на сайте");
    }


}
