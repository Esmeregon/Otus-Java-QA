package com.example.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import pages.EventPlatformToolbar;
import pages.EventViewPage;
import pages.EventsPage;
import pages.TalksLibraryPage;
import utils.SeleniumSettings;

import java.text.ParseException;


@SpringBootTest
public class ApplicationTests extends SeleniumSettings {

    /**
     * Просмотр предстоящих мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events
     */
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Otus")
    @Feature("")
    @Description("")
    public void viewUpcomingEvents() {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform();
        eventPlatformToolbar.goToEvents();
        eventsPage.goToUpcomingEventsTab();
        eventsPage.checkingUpcomingEventCardsCount();
    }

    /**
     * Просмотр карточек мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Past Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 В карточке указана информация о мероприятии:
     *      язык
     *      название мероприятия
     *      дата мероприятия
     *      информация о регистрации
     *      список спикеров // Минимально достаточное - проверить одну карточку. В идеале все что отображаются.
     */
    @Test
    public void viewCardInfo() {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform();
        eventPlatformToolbar.goToEvents();
        eventsPage.goToPastEventsTab();
        eventsPage.checkingEventCards();
    }

    /**
     * Валидация дат предстоящих мероприятий:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Upcoming Events
     * 3 На странице отображаются карточки предстоящих мероприятий.
     * 4 Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)
     */
    @Test
    public void dateValidation() throws ParseException {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform();
        eventPlatformToolbar.goToEvents();
        eventsPage.goToUpcomingEventsTab();
        eventsPage.checkingEventDates();
    }

    /**
     * Просмотр прошедших мероприятий в Канаде:
     * 1 Пользователь переходит на вкладку events
     * 2 Пользователь нажимает на Past Events
     * 3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
     * 4 На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику на кнопке Past Events. Даты проведенных мероприятий меньше текущей даты.
     */
    @Test
    public void viewPastEvents() throws ParseException {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        EventsPage eventsPage = PageFactory.initElements(driver, EventsPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform();
        eventPlatformToolbar.goToEvents();
        eventsPage.goToPastEventsTab();
        eventsPage.filtrationByLocation();
        eventsPage.checkingPastEventCardsCount();
        eventsPage.checkingDatesPastEvent();
    }

    /**
     * Фильтрация докладов по категориям:
     * 1 Пользователь переходит на вкладку Talks Library
     * 2 Пользователь нажимает на More Filters
     * 3 Пользователь выбирает: Category – Testing, Location – Belarus, Language – English, На вкладке фильтров
     * 4 На странице отображаются карточки соответствующие правилам выбранных фильтров
     */
    @Test
    public void filteringReportsByCategory (){
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        TalksLibraryPage talksLibraryPage = PageFactory.initElements(driver, TalksLibraryPage.class);
        EventViewPage eventViewPage = PageFactory.initElements(driver, EventViewPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform();
        eventPlatformToolbar.goToTalksLibrary();
        talksLibraryPage.viewFilters();
        talksLibraryPage.selectALanguage();
        talksLibraryPage.selectACategory();
        talksLibraryPage.selectALocation();
        talksLibraryPage.checkingCard();
        eventViewPage.checkingEventDetail();
    }


    /**
     * Поиск докладов по ключевому слову:
     * 1 Пользователь переходит на вкладку VIDEO - Talks Library
     * 2 Пользователь вводит ключевое слово QA в поле поиска
     * 3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
     */
    @Test
    public void speechSearchByKeyword () {
        EventPlatformToolbar eventPlatformToolbar = PageFactory.initElements(driver, EventPlatformToolbar.class);
        TalksLibraryPage talksLibraryPage = PageFactory.initElements(driver, TalksLibraryPage.class);

        eventPlatformToolbar.goToEventsDigitalPlatform();
        eventPlatformToolbar.goToTalksLibrary();
        talksLibraryPage.searchByKeyword();
    }
}
