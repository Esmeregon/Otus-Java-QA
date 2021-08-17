package com.example.tests.pages;

import com.example.tests.ApplicationTests;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


/**
 * В классе EventsPage реализована логика со страницей Events
 */
public class EventsPage {

    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By upcomingEventsTab = By.xpath("//span[contains(text(),'Upcoming events')]");
    private final By pastEventsTab = By.xpath("//span[contains(text(),'Past Events')]");
    public final By upcomingEventsCount = By.xpath("//span[contains(text(),'Upcoming events')]/following-sibling::span[@class = 'evnt-tab-counter evnt-label small white']");
    public final By pastEventsCount = By.xpath("//span[contains(text(),'Past Events')]/following-sibling::span[@class = 'evnt-tab-counter evnt-label small white']");
    private final By eventCards = By.xpath("//div[@class = 'evnt-events-column cell-3']//div[@class = 'evnt-card-wrapper']");
    public final By eventLanguage = By.xpath("//p[@class = 'language']");
    public final By eventTitle = By.xpath("//h1[span]");
    public final By eventDate = By.xpath("//span[@class = 'date']");
    public final By eventRegistrationStatusClosed = By.xpath("//span[@class = 'status reg-close']");
    public final By eventSpeaker = By.xpath("//div[@class = 'evnt-speaker']");
    private final By locationFilter = By.xpath("//div[@id= 'filter_location']");

    /**
     * В методе EventsPage() происходит инициализация веб-драйвера, а также настравается ожидание
     *
     * @param driver веб-драйвер
     */
    public EventsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * В методе goToUpcomingEventsTab() осуществляется переход на вкладку Upcoming Events
     */
    @Step("Go to the Upcoming Events tab")
    public void goToUpcomingEventsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTab));
        driver.findElement(upcomingEventsTab).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventCards));
        logger.info("Пользователь переходит на Upcoming Events");
    }

    /**
     * В методе eventCards() осуществляется добавление катрточек событий в List
     *
     * @return List событий
     */
    public List<WebElement> eventCards() {
        return new ArrayList<>(driver.findElements(eventCards));
    }

    /**
     * В методе goToPastEventsTab() осуществляется переход на вкладку Past Events
     */
    @Step("Go to the Past Events tab")
    public void goToPastEventsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pastEventsTab));
        driver.findElement(pastEventsTab).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(eventCards).get(0)));
        logger.info("Пользователь переходит на Past Events");
    }

    /**
     * В методе filtrationByLocation() осуществляется фильтрация карточек по территории
     *
     * @param location территории
     */
    @Step("Filtering by Location")
    public void filtrationByLocation(String location) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventCards));
        driver.findElement(locationFilter).click();
        driver.findElement(By.xpath("//label[@data-value= '" + location + "']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(eventCards).get(0)));
        logger.info("Отфильтровали по Location - {}", location);
    }
}
