package pages;

import com.example.tests.ApplicationTests;
import com.example.tests.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public EventsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }


    private final By upcomingEventsTab = By.xpath("//span[contains(text(),'Upcoming events')]");
    private final By pastEventsTab = By.xpath("//span[contains(text(),'Past Events')]");

    private final By upcomingEventsCount = By.xpath("//span[contains(text(),'Upcoming events')]/following-sibling::span[@class = 'evnt-tab-counter evnt-label small white']");
    private final By pastEventsCount = By.xpath("//span[contains(text(),'Past Events')]/following-sibling::span[@class = 'evnt-tab-counter evnt-label small white']");
    private final By eventCards = By.xpath("//div[@class = 'evnt-events-column cell-3']//div[@class = 'evnt-card-wrapper']");

    private final By eventLanguage = By.xpath("//p[@class = 'language']");
    private final By eventTitle = By.xpath("//h1[span]");
    private final By eventDate = By.xpath("//span[@class = 'date']");
    private final By eventRegistrationStatusClosed = By.xpath("//span[@class = 'status reg-close']");
    private final By eventSpeaker = By.xpath("//div[@class = 'evnt-speaker']");

    private final By locationFilter = By.xpath("//div[@id= 'filter_location']");



    public void goToUpcomingEventsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(upcomingEventsTab));
        driver.findElement(upcomingEventsTab).click();
        logger.info("Пользователь переходит на Upcoming Events");
    }

    public void checkingUpcomingEventCardsCount(){
        wait.until(ExpectedConditions.elementToBeClickable(upcomingEventsTab));
        Assertions.assertEquals(eventCards().size(), Integer.parseInt(driver.findElement(upcomingEventsCount).getText()));
        logger.info("На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events");
    }

    public List<WebElement> eventCards() {
        return new ArrayList<>(driver.findElements(eventCards));
    }


    public void checkingEventCards() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventCards));
        for (int i = 0; i < eventCards().size(); i++){
            Assertions.assertNotNull(driver.findElements(eventLanguage).get(i).getText());
            Assertions.assertNotNull(driver.findElements(eventTitle).get(i).getText());
            Assertions.assertNotNull(driver.findElements(eventDate).get(i).getText());
            Assertions.assertNotNull(driver.findElements(eventRegistrationStatusClosed).get(i).getText());
            Assertions.assertNotNull(driver.findElements(eventSpeaker).get(i).getText());
        }
        logger.info("Проверили заполнение карточек мероприятий");
    }

    public void goToPastEventsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pastEventsTab));
        driver.findElement(pastEventsTab).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(eventCards).get(0)));
        logger.info("Пользователь переходит на Past Events");
    }

    public void filtrationByLocation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventCards));
        driver.findElement(locationFilter).click();
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.location() +"']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(eventCards).get(0)));
        logger.info("Отфильтровали по Location");
    }

/**ToDo: можно объединить с checkingEventCardCount
 * ToDo: проверка на дату
 */

    public void checkingPastEventCardsCount(){
        wait.until(ExpectedConditions.elementToBeClickable(upcomingEventsTab));
        Assertions.assertEquals(eventCards().size(), Integer.parseInt(driver.findElement(pastEventsCount).getText()));
        logger.info("На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Past Events");
    }

    public void checkingDatesPastEvent() throws ParseException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventCards));
        for (int i = 0; i < eventCards().size(); i++){
            String endEventDate = getEndEventDate(driver.findElements(eventDate).get(i).getText());
            Assertions.assertTrue((dateParser(endEventDate).before(getSystemDate())));
        }
        logger.info("Проверили даты прошедших событий");
    }


    public void choiceOfEvent() {
        wait.until(ExpectedConditions.elementToBeClickable(eventCards));
        driver.findElements(eventCards).get(0).click();
        logger.info("Выбрали первую карточку");
    }


    public void checkingEventDates() throws ParseException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventCards));
        for (int i = 0; i < eventCards().size(); i++){
            String startEventDate = getStartEventDate(driver.findElements(eventDate).get(i).getText());
            String endEventDate = getEndEventDate(driver.findElements(eventDate).get(i).getText());
            Assertions.assertTrue((getSystemDate().before(dateParser(startEventDate)) || getSystemDate().after(dateParser(endEventDate))) || (getSystemDate().before(dateParser(endEventDate))));
        }
        logger.info("Проверели даты проведения событий");
    }




    public Date dateParser(String date) throws ParseException {
     //   Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("d MMM yyyy", Locale.US);

        //String date = "14 Jul - 12 Aug 2021";
     //   String beforeDate = date.split(" - ")[0] + " " + date.substring(date.lastIndexOf(" ")+1);
     //   String afterDate = date.split(" - ")[1];

     //   String localDate = formatForDateNow.format(dateNow);

        //     Date after = formatForDateNow.parse(afterDate);
       // Date local = formatForDateNow.parse(localDate);
     //   Assertions.assertFalse(local.before(before) || local.after(after));

      //  System.out.println(beforeDate);
      //  System.out.println(afterDate);
       // System.out.println(formatForDateNow.format(dateNow));
        return formatForDateNow.parse(date);
    }

    /**
     *
     * @return системное время
     * @throws ParseException
     */
    public Date getSystemDate() throws ParseException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("d MMM yyyy", Locale.US);

        String localDate = formatForDateNow.format(dateNow);
        return formatForDateNow.parse(localDate);
    }

    /**
     *
     * @param eventDates диапазон дат
     * @return дата начала события
     */
    public String getStartEventDate(String eventDates) {
        return eventDates.split(" - ")[0] + " " + eventDates.substring(eventDates.lastIndexOf(" ")+1);
    }

    /**
     *
     * @param eventDates диапазон дат
     * @return дату окончания события
     */
    public String getEndEventDate(String eventDates) {
        return eventDates.split(" - ")[1];
    }

}
