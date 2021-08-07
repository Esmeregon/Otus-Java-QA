package pages;

import com.example.tests.ApplicationTests;
import com.example.tests.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TalksLibraryPage {


    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public TalksLibraryPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    private final By moreFiltersButton = By.xpath("//div[@class = 'evnt-toogle-filters-text show-more']");
    private final By categoryFilter = By.xpath("//div[@id= 'filter_category']");
    private final By locationFilter = By.xpath("//div[@id= 'filter_location']");
    private final By languageFilter = By.xpath("//div[@id= 'filter_language']");

    private final By talksCard = By.xpath("//div[@class = 'evnt-card-table']");

    private final By searchField = By.xpath("//div[@class= 'evnt-search-filter']//input");
    private final By cardLabel = By.xpath("//h1[@data-original-title]");


    public void viewFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(moreFiltersButton));
        driver.findElement(moreFiltersButton).click();
        logger.info("Нажали на More Filters");
    }


    public void selectACategory(){
        wait.until(ExpectedConditions.elementToBeClickable(categoryFilter));
        driver.findElement(categoryFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//label[@data-value= '" + cfg.category() +"']"))));
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.category() +"']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        driver.findElement(categoryFilter).click();
        logger.info("Отфильтровали по Category");
    }

    public void selectALocation(){
        wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
        driver.findElement(locationFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//label[@data-value= '" + cfg.libraryLocation() +"']"))));
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.libraryLocation() +"']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        driver.findElement(locationFilter).click();
        logger.info("Отфильтровали по Location");
    }

    public void selectALanguage(){
        wait.until(ExpectedConditions.elementToBeClickable(languageFilter));
        driver.findElement(languageFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@data-value= '" + cfg.language() +"']")));
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.language() +"']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        driver.findElement(languageFilter).click();
        logger.info("Отфильтровали по Language");
    }

    public void checkingCard() {
        wait.until(ExpectedConditions.elementToBeClickable(talksCard));
        driver.findElements(talksCard).get(0).click();
        logger.info("Переходим на карточку события");
    }

    public void searchByKeyword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(cfg.keyword());
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        for (int i = 0; i < driver.findElements(talksCard).size(); i++){
            Assertions.assertTrue(driver.findElement(cardLabel).getText().contains(cfg.keyword()));
        }
        logger.info("Осуществили поиск по ключевому слову");
    }

}
