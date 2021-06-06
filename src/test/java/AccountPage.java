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
 * В классе AccountPage реализована логика заполниения персональных данных пользователя на странице личного кабинета,
 * а также происходит добавление дополнительных контактных данных в List для дальнейшей обработки
 */

public class AccountPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(Tests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    List<String>contacts = new ArrayList<>();

    public final By nameField = By.xpath("//input[@data-title = 'Имя']");
    public final By surnameField = By.xpath("//input[@data-title = 'Фамилия']");
    private final By contactValueField = By.xpath("//input[@class= 'input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8']");
    private final By communicationMethod = By.xpath("//div[@class = 'select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select']");
    private final By deleteContactButton = By.xpath("//div[@class = 'container__col container__col_12 container__col_md-0']//button[contains(text(),'Удалить')]");
    private final By addContactButton = By.xpath("//button[contains(text(),'Добавить')]");
    private final By newCommunicationMethod = By.xpath("//div[@class= 'lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']//button[@title= 'WhatsApp']");
    private final By saveButton = By.xpath("//button[@title= 'Сохранить и продолжить']");
    private final By alterMethodCount = By.xpath("//div[@class = 'container__row js-formset-row']//input[@class= 'input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8']");

/**
 * В методе AccountPage происходит инициализация веб-драйвера,
 * а также настравается ожидание
 * @param driver веб-драйвер
 * */
    public AccountPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

/**
 *  В методе fillingPersonalData() заполняются личные данные, а также добавляются дополнительные способы связи.
 * */
    public void fillingPersonalData() {
        int baseCountOfMethods = driver.findElements(alterMethodCount).size();
        int finalCountOfMethods = baseCountOfMethods + cfg.countMethods();

        wait.until(ExpectedConditions.elementToBeClickable(addContactButton));
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(cfg.name());
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(cfg.surname());

        if (driver.findElement(communicationMethod).getText().equals("Способ связи")){
            driver.findElements(deleteContactButton).get(0).click();
        }

        for (int i = baseCountOfMethods; i < finalCountOfMethods; i++){
            String contact = "test" + System.currentTimeMillis();
            driver.findElement(addContactButton).click();
            driver.findElements(communicationMethod).get(i).click();
            driver.findElement(newCommunicationMethod).click();
            driver.findElements(contactValueField).get(i).sendKeys(contact);
        }
        contacts = communicationMethods();
        driver.findElement(saveButton).click();
        logger.info("Заполнили персональные данные");
    }

/**
 * В методе communicationMethods() происходит добавление дополнгительных способов связи в List
 * */
    public List<String> communicationMethods() {
        List<String>resultContacts = new ArrayList<>();
        for (int i = 0; i < driver.findElements(alterMethodCount).size(); i++) {
            resultContacts.add(driver.findElements(alterMethodCount).get(i).getAttribute("value"));
        }
        return resultContacts;
    }

}


