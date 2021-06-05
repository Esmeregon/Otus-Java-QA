import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;


public class Tests extends SeleniumSettings{

    String email = "";
    String password = "";

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


    @Test
    public void checkingProductComparison(){
        AccountPage accountPage = PageFactory.initElements(driver, AccountPage.class);
        CoursesPage coursesPage = PageFactory.initElements(driver, CoursesPage.class);
        SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);

        coursesPage.goToOtus();
        signInPage.logIn(email, password);
        coursesPage.accountLink();
        accountPage.personalInfoMenu();
        accountPage.completePersonalInfo();

        driver.manage().deleteAllCookies();

        coursesPage.goToOtus();
        signInPage.logIn(email, password);
        coursesPage.accountLink();
        accountPage.personalInfoMenu();

        Assert.assertEquals(cfg.name(), driver.findElement(accountPage.nameField).getAttribute("value"));
        Assert.assertEquals(cfg.surname(), driver.findElement(accountPage.surnameField).getAttribute("value"));
        Assert.assertEquals(accountPage.contacts, accountPage.communicationMethods());
    }

}
