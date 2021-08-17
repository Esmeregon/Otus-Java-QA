package com.example.tests;

import com.example.tests.pages.EventPlatformToolbar;
import com.example.tests.pages.EventViewPage;
import com.example.tests.pages.EventsPage;
import com.example.tests.pages.TalksLibraryPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.support.PageFactory;
import utils.Base;
import utils.SeleniumSettings;

import java.text.ParseException;

@Execution(ExecutionMode.CONCURRENT)
@Epic("Otus")
public class ApplicationTests extends SeleniumSettings {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);

    /**
     * Просмотр предстоящих мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events
     */
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("View upcoming events")
    @Description("The test describes a scenario in which the user goes to the events tab. The page displays cards for upcoming events. The number of cards is equal to the counter on the Upcoming Events button ")
    public void viewUpcomingEvents() {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform(cfg.url());
        eventPlatformToolbar.goToEvents();
        Assertions.assertEquals(eventsPage.eventCards().size(), Integer.parseInt(driver.findElement(eventsPage.upcomingEventsCount).getText()), "Compared the number of cards and the counter value on the Upcoming Events button");
        logger.info("Убедились, что оличество карточек равно счетчику на кнопке Upcoming Events");
    }

    /**
     * Просмотр карточек мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Past Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 В карточке указана информация о мероприятии:
     * язык
     * название мероприятия
     * дата мероприятия
     * информация о регистрации
     * список спикеров // Минимально достаточное - проверить одну карточку. В идеале все что отображаются.
     */
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("View card info")
    @Description("The test describes a scenario in which the event cards are viewed. The user navigates to the events tab. Goes to the Past Events tab. The page displays cards for upcoming events. The card contains information about the event")
    public void viewCardInfo() {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform(cfg.url());
        eventPlatformToolbar.goToEvents();
        eventsPage.goToPastEventsTab();
        for (int i = 0; i < eventsPage.eventCards().size(); i++) {
            Assertions.assertNotNull(driver.findElements(eventsPage.eventLanguage).get(i).getText(), "Checking the presence of the language of the event in the card");
            Assertions.assertNotNull(driver.findElements(eventsPage.eventTitle).get(i).getText(), "Checking the presence of the title of the event in the card");
            Assertions.assertNotNull(driver.findElements(eventsPage.eventDate).get(i).getText(), "Checking the presence of the date of the event in the card");
            Assertions.assertNotNull(driver.findElements(eventsPage.eventRegistrationStatusClosed).get(i).getText(), "Checking the presence of the registration status of the event in the card");
            Assertions.assertNotNull(driver.findElements(eventsPage.eventSpeaker).get(i).getText(), "Checking the presence of the speakers of the event in the card");
        }
        logger.info("Проверили заполнение карточек мероприятий");
    }

    /**
     * Валидация дат предстоящих мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Upcoming Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)
     */
    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Date validation")
    @Description("The test describes a scenario in which the validation of the dates of upcoming events is checked. The user navigates to the Upcoming Events tab. The page displays cards of upcoming events, the dates of which are greater than or equal to the current date (or the current date is in the range of dates) ")
    public void dateValidation() throws ParseException {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);
        Base base = new Base();

        eventPlatformToolbar.goToEventsDigitalPlatform(cfg.url());
        eventPlatformToolbar.goToEvents();
        eventsPage.goToUpcomingEventsTab();
        for (int i = 0; i < eventsPage.eventCards().size(); i++) {
            String startEventDate = base.getStartEventDate(driver.findElements(eventsPage.eventDate).get(i).getText());
            String endEventDate = base.getEndEventDate(driver.findElements(eventsPage.eventDate).get(i).getText());
            Assertions.assertTrue((base.getSystemDate().before(base.dateParser(startEventDate)) || base.getSystemDate().after(base.dateParser(endEventDate))) || (base.getSystemDate().before(base.dateParser(endEventDate))),
                    "Check if the dates of the events are greater than or equal to the current date (or the current date is in the range of dates)");
        }
        logger.info("Убедились, что даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)");
    }

    /**
     * Просмотр прошедших мероприятий в Канаде:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Past Events
     * 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
     * 4 На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику на кнопке Past Events. Даты проведенных мероприятий меньше текущей даты.
     */
    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("View past events")
    @Description("The test describes a scenario within which a review of past events is carried out. The user navigates to the Past Events tab. Then the events are filtered. The number of cards is equal to the counter on the Past Events button. The dates of the events held are less than the current date ")
    public void viewPastEvents() throws ParseException {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);
        Base base = new Base();

        eventPlatformToolbar.goToEventsDigitalPlatform(cfg.url());
        eventPlatformToolbar.goToEvents();
        eventsPage.goToPastEventsTab();
        eventsPage.filtrationByLocation(cfg.location());
        Assertions.assertEquals(eventsPage.eventCards().size(), Integer.parseInt(driver.findElement(eventsPage.pastEventsCount).getText()), "Compared the number of cards and the counter value on the Past Events button");
        logger.info("Убедились, что количество карточек равно счетчику на кнопке Past Events");
        for (int i = 0; i < eventsPage.eventCards().size(); i++) {
            String endEventDate = base.getEndEventDate(driver.findElements(eventsPage.eventDate).get(i).getText());
            Assertions.assertTrue((base.dateParser(endEventDate).before(base.getSystemDate())), "Checking that the dates of the events held are less than the current date");
        }
        logger.info("Убедились, что даты проведенных мероприятий меньше текущей даты");
    }

    /**
     * Фильтрация докладов по категориям:
     * 1 Пользователь переходит на вкладку Talks Library
     * 2 Пользователь нажимает на More Filters
     * 3 Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, На вкладке фильтров
     * 4 На странице отображаются карточки соответствующие правилам выбранных фильтров
     */
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Filtering reports by category")
    @Description("The test describes a scenario in which reports are filtered by categories. The user navigates to the Talks Library tab. Then the events are filtered. The page displays cards that match the rules of the selected filters ")
    public void filteringReportsByCategory() {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        TalksLibraryPage talksLibraryPage = PageFactory.initElements(driver, TalksLibraryPage.class);
        EventViewPage eventViewPage = PageFactory.initElements(driver, EventViewPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform(cfg.url());
        eventPlatformToolbar.goToTalksLibrary();
        talksLibraryPage.clickedOnMoreFilters();
        talksLibraryPage.selectALanguageFilter(cfg.language());
        talksLibraryPage.selectACategoryFilter(cfg.category());
        talksLibraryPage.selectALocationFilter(cfg.libraryLocation());
        talksLibraryPage.goToEventCard();
        Assertions.assertEquals(cfg.language(), driver.findElement(eventViewPage.eventLanguage).getText(), "Checking that the page displays cards matching the filter language");
        Assertions.assertEquals(cfg.category(), driver.findElement(eventViewPage.eventCategory).getText(), "Checking that the page displays cards matching the filter category");
        Assertions.assertTrue(driver.findElement(eventViewPage.eventLocation).getText().contains(cfg.libraryLocation()), "Checking that the page displays cards matching the filter location");
        logger.info("Проверили, что карточка соответствует критериям поиска");
    }

    /**
     * Поиск докладов по ключевому слову:
     * 1 Пользователь переходит на вкладку VIDEO - Talks Library
     * 2 Пользователь вводит ключевое слово QA в поле поиска
     * 3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
     */
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Speech search by keyword")
    @Description("The test describes a scenario in which the search for reports by a keyword is carried out. The user navigates to the Talks Library tab. Then he enters the keyword in the search field. The page displays reports containing the search keyword in the title ")
    public void speechSearchByKeyword() {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        TalksLibraryPage talksLibraryPage = PageFactory.initElements(driver, TalksLibraryPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform(cfg.url());
        eventPlatformToolbar.goToTalksLibrary();
        talksLibraryPage.searchByKeyword(cfg.keyword());
        for (int i = 0; i < driver.findElements(talksLibraryPage.talksCard).size(); i++) {
            Assertions.assertTrue(driver.findElement(talksLibraryPage.cardLabel).getText().contains(cfg.keyword()), "Check that the page displays reports containing the search keyword in the title");
        }
        logger.info("Убедились, что на странице отображаются доклады, содержащие в названии ключевое слово поиска - {}", cfg.keyword());
    }
}

