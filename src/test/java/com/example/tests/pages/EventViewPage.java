package com.example.tests.pages;

import com.example.tests.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;

/**
 * В классе EventViewPage реализована логика работы со страницей просмотра карточки события
 */
public class EventViewPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    public final By eventLanguage = By.xpath("//div[@class= 'evnt-talk-details language evnt-now-past-talk']");
    public final By eventCategory = By.xpath("//label[contains(text(),'" + cfg.category() + "')]");
    public final By eventLocation = By.xpath("//span[contains(text(),'" + cfg.libraryLocation() + "')]");
}
