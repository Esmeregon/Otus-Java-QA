import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


/**
 * В классе MarketPage реализована основная логика работы приложения:
 *      переход на сайт интернет-магазина,
 *      фильтрация товаров по производителям,
 *      сортировка результатов поиска,
 *      сравнение товаров
 */

public class AccountPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(Tests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    List<String>contacts = new ArrayList<>();
    int methods;
    int resultSize;


    public AccountPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    private final By personalInfo = By.xpath("//div[@class = 'nav__items']/a[@href='/lk/biography/personal/']");
    private final By personalInfoHeader = By.xpath("//h3[contains( text(),'Персональные данные')]");
    public final By nameField = By.xpath("//input[@data-title = 'Имя']");
    public final By surnameField = By.xpath("//input[@data-title = 'Фамилия']");
    private final By addContactButton = By.xpath("//button[contains( text(),'Добавить')]");
    private final By defaultMethod = By.xpath("//div[@class= 'input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']");
    private final By placeHolder= By.xpath("//div[@class = 'container__row js-formset-row']//div[@class= 'input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']");
    private final By contactMethod = By.xpath("//div[@class= 'lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']//button[@title= 'WhatsApp']");
    private final By contactField = By.xpath("//input[@class= 'input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8']");
    private final By saveButton = By.xpath("//button[@title= 'Сохранить и продолжить']");
    private final By deleteButton = By.xpath("//div[@class = 'container__col container__col_12 container__col_md-0']//button[contains( text(),'Удалить')]");
    private final By alterMethodCount = By.xpath("//div[@class = 'container__row js-formset-row']//input[@class= 'input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8']");

    public void personalInfoMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(personalInfo));
        driver.findElement(personalInfo).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(personalInfoHeader));
        logger.info("Перешли на форму 'О себе'");
    }

    public void completePersonalInfo() {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(cfg.name());
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(cfg.surname());

        methods = driver.findElements(contactField).size();
        resultSize = methods + cfg.countMethods();

        if (driver.findElement(placeHolder).getText().equals("Способ связи")){
            driver.findElements(deleteButton).get(0).click();
        }

        for (int i = methods; i < resultSize; i++){
            String contact = "test" + System.currentTimeMillis();

            driver.findElement(addContactButton).click();
            driver.findElements(defaultMethod).get(i).click();
            driver.findElement(contactMethod).click();
            driver.findElements(contactField).get(i).sendKeys(contact);
        }
        contacts = communicationMethods();
        driver.findElement(saveButton).click();
    }

    public List<String> communicationMethods() {
        List<String>resultContacts = new ArrayList<>();
        for (int i = 0; i < driver.findElements(alterMethodCount).size(); i++) {
            resultContacts.add(driver.findElements(alterMethodCount).get(i).getAttribute("value"));
            System.out.println(resultContacts.get(i));
        }
        return resultContacts;
    }

}


