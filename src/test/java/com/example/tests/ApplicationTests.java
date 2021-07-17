package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;



@SpringBootTest
public class ApplicationTests {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
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


    @BeforeEach
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    public void end(){
        if (driver!=null)
            driver.quit();
    }


    /**
     *  В методе checkingContacts() происходит проверка страницы Контакты
     */
    @Test
    @Severity(SeverityLevel.MINOR)
    @Epic("Otus")
    @Feature("Adding a title to the page")
    @Description("Checking the page title ")
    public void checkingContacts(){
        String address = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        driver.get(cfg.urlOtus());
        logger.info("Перешли на сайт " + cfg.urlOtus());
        driver.findElement(contactsLink).click();
        logger.info("Перешли на вкладку Контакты");
        Assertions.assertEquals(address,  driver.findElement(addressInfo).getText());
        logger.info("Проверили адрес");
        driver.manage().window().maximize();
        logger.info("Развернули окно браузера на полный экран");
        Assertions.assertEquals("Контакты | OTUS", driver.getTitle());
        Allure.addAttachment("checkingContacts", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Проверили title страницы");
    }


    /**
     *  В методе checkingTele2Numbers() происходит проверка обновления списка телефонных номеров при вводе в поисковую
     *  строку на сайте Теле2
     */
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Tele2")
    @Feature("Find a phone number")
    @Description("Checking the correctness of the phone number search ")
    public void checkingTele2Numbers(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(cfg.urlTele2());
        logger.info("Перешли на сайт " + cfg.urlTele2());
        wait.until(ExpectedConditions.elementToBeClickable(phoneNumbers));
        driver.findElement(searchNumberField).clear();
        driver.findElement(searchNumberField).sendKeys("97");
        logger.info("Заполнили поле 'поиск номера'");
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(phoneNumbers).get(0)));
        Assertions.assertNotNull(driver.findElement(phoneNumbers).getText());
        Allure.addAttachment("checkingNumbers", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Проверили телефонные номера");
    }


    /**
     *  В методе checkingFAQ() происходит проверка наличия ответа на вопрос на строаницы FAQ на сайте Otus.ru
     */
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Otus")
    @Feature("FAQ display")
    @Description("Checking the correctness of the FAQ display ")
    public void checkingFAQ(){
        String faqInfo = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        driver.get(cfg.urlOtus());
        logger.info("Перешли на сайт " + cfg.urlOtus());
        driver.findElement(faqLink).click();
        logger.info("Перешли на вкладку FAQ");
        driver.findElement(faqQuestion).click();
        logger.info("Выбрали вопрос");
        Assertions.assertEquals(faqInfo,  driver.findElement(faqAnswer).getText());
        Allure.addAttachment("checkingFAQ", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Проверили ответ");
    }


    /**
     *  В методе checkingSubscribe() происходит проверка подписки на расслылку на сайте Otus.ru
     */
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Otus")
    @Feature("Subscription to newsletter")
    @Description("Checking subscription to newsletter")
    public void checkingSubscribe(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(cfg.urlOtus());
        logger.info("Перешли на сайт " + cfg.urlOtus());
        driver.findElement(subscribeField).clear();
        logger.info("Очистили поле 'Электронная почта'");
        driver.findElement(subscribeField).sendKeys("test" + System.currentTimeMillis() + "@test.ru");
        logger.info("Ввели электронную почту");
        driver.findElement(subscribeButton).click();
        logger.info("Нажали на кнопрку 'Подписаться'");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successSubscribeMessage));
        Assertions.assertEquals("Вы успешно подписались", driver.findElement(successSubscribeMessage).getText());
        Allure.addAttachment("checkingSubscribe", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Проверили сообщение");
    }
}
