import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;


public class Tests {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(Tests.class);
    protected static WebDriver driver;

    private final By contactsLink = By.xpath("//a[@class= 'header2_subheader-link'][@href = '/contacts/']");
    private final By addressInfo = By.xpath("//div[contains( text(),'Адрес')]/following-sibling::*");
    private final By searchNumberField = By.xpath("//input[@id='searchNumber']");
    private final By phoneNumbers = By.xpath("//span[@class='phone-number']");
    private final By faqLink = By.xpath("//a[@class = 'header2_subheader-link'][@href= '/faq/']");
    private final By faqQuestion = By.xpath("//div[contains( text(),'Где посмотреть программу интересующего курса?')]");
    private final By faqAnswer = By.xpath("//div[contains( text(),'Где посмотреть программу интересующего курса?')]/following-sibling::*");
    private final By subscribeField = By.xpath("//input[@class = 'input footer2__subscribe-input']");
    private final By subscribeButton = By.xpath("//button[@class = 'footer2__subscribe-button button button_blue button_as-input']");
    private final By successSubscribeMessage = By.xpath("//p[@class = 'subscribe-modal__success']");




    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void end(){
        if (driver!=null)
            driver.quit();
    }


    @Test
    public void checkingContacts(){
        String address = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        driver.get(cfg.urlOtus());
        logger.info("Перешли на сайт otus.ru");
        driver.findElement(contactsLink).click();
        logger.info("Перешли на вкладку Контакты");
        Assert.assertEquals(address,  driver.findElement(addressInfo).getText());
        logger.info("Проверили адрес");
        driver.manage().window().maximize();
        logger.info("Развернули окно браузера на полный экран");
        Assert.assertEquals("Контакты | OTUS", driver.getTitle());
        logger.info("Проверили title страницы");
    }


    @Test
    public void checkingTele2Numbers(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(cfg.urlTele2());
        logger.info("Перешли на сайт Tele2");
        wait.until(ExpectedConditions.elementToBeClickable(phoneNumbers));
        driver.findElement(searchNumberField).clear();
        driver.findElement(searchNumberField).sendKeys("97");
        logger.info("Заполнили поле 'поиск номера'");
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(phoneNumbers).get(0)));
        Assert.assertNotNull(driver.findElement(phoneNumbers).getText());
        logger.info("Проверили телефонные номера");
    }

    @Test
    public void checkingFAQ(){
        String faqInfo = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        driver.get(cfg.urlOtus());
        logger.info("Перешли на сайт otus.ru");
        driver.findElement(faqLink).click();
        logger.info("Перешли на вкладку FAQ");
        driver.findElement(faqQuestion).click();
        logger.info("Выбрали вопрос");
        Assert.assertEquals(faqInfo,  driver.findElement(faqAnswer).getText());
        logger.info("Проверили ответ");
    }

    @Test
    public void checkingSubscribe(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(cfg.urlOtus());
        logger.info("Перешли на сайт otus.ru");
        driver.findElement(subscribeField).clear();
        logger.info("Очистили поле 'Электронная почта'");
        driver.findElement(subscribeField).sendKeys("test" + System.currentTimeMillis() + "@test.ru");
        logger.info("Ввели электронную почту");
        driver.findElement(subscribeButton).click();
        logger.info("Нажали на кнопрку 'Подписаться'");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successSubscribeMessage));
        Assert.assertEquals("Вы успешно подписались", driver.findElement(successSubscribeMessage).getText());
        logger.info("Проверили сообщение");
    }

}

