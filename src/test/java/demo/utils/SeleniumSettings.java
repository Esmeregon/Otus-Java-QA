package demo.utils;

import demo.pages.AccountPage;
import demo.pages.CoursesPage;
import demo.pages.SignInPage;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;

public class SeleniumSettings {

    WebDriver driver;
    protected final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    public CoursesPage coursesPage;
    public SignInPage signInPage;
    public AccountPage accountPage;


    /**
     * В методе startUp() осуществляется конфигурирование драйвера для запуска приложения
     */
    public void startUp(){
        driver = WebDriverFactory.createDriver(Browsers.valueOf(cfg.browser().toUpperCase(Locale.ROOT)));
        assert driver != null;
        driver.manage().window().maximize();
        coursesPage = PageFactory.initElements(driver, CoursesPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
    }

    /**
     * В методе end() описана логика работы драйвера при завершении работы приложения
     */
    public void end(){
        if (driver!=null)
            driver.quit();
    }
}