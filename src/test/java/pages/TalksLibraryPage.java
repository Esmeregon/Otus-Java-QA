package pages;

import com.example.tests.ApplicationTests;
import com.example.tests.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * В классе TalksLibraryPage реализована логика работы о страницей Talks Library
 */
public class TalksLibraryPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(ApplicationTests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By moreFiltersButton = By.xpath("//div[@class = 'evnt-toogle-filters-text show-more']");
    private final By categoryFilter = By.xpath("//div[@id= 'filter_category']");
    private final By locationFilter = By.xpath("//div[@id= 'filter_location']");
    private final By languageFilter = By.xpath("//div[@id= 'filter_language']");
    public final By talksCard = By.xpath("//div[@class = 'evnt-card-table']");
    private final By searchField = By.xpath("//div[@class= 'evnt-search-filter']//input");
    public final By cardLabel = By.xpath("//h1[@data-original-title]");
    public final By eventLanguage = By.xpath("//div[@class= 'evnt-talk-details language evnt-now-past-talk']");

    /**
     * В методе TalksLibraryPage() происходит инициализация веб-драйвера, а также настравается ожидание
     *
     * @param driver веб-драйвер
     */
    public TalksLibraryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * В методе viewFilters() осуществляется раскрытие панели фильтров
     */
    public void viewFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(moreFiltersButton));
        driver.findElement(moreFiltersButton).click();
        logger.info("Нажали на More Filters");
    }

    /**
     * В методе selectACategory() осуществляется фильтрация по категории
     */
    public void selectACategory() {
        wait.until(ExpectedConditions.elementToBeClickable(categoryFilter));
        driver.findElement(categoryFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//label[@data-value= '" + cfg.category() + "']"))));
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.category() + "']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        driver.findElement(categoryFilter).click();
        logger.info("Отфильтровали по Category");
    }

    /**
     * В методе selectALocation() осуществляется фильтрация по территории
     */
    public void selectALocation() {
        wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
        driver.findElement(locationFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//label[@data-value= '" + cfg.libraryLocation() + "']"))));
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.libraryLocation() + "']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        driver.findElement(locationFilter).click();
        logger.info("Отфильтровали по Location");
    }

    /**
     * В методе selectALanguage() осуществляется фильтрация по языку
     */
    public void selectALanguage() {
        wait.until(ExpectedConditions.elementToBeClickable(languageFilter));
        driver.findElement(languageFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@data-value= '" + cfg.language() + "']")));
        driver.findElement(By.xpath("//label[@data-value= '" + cfg.language() + "']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        driver.findElement(languageFilter).click();
        logger.info("Отфильтровали по Language");
    }

    /**
     * В методе checkingCard() осуществляется переход на карточку события
     */
    public void checkingCard() {
        wait.until(ExpectedConditions.elementToBeClickable(talksCard));
        driver.findElements(talksCard).get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventLanguage));
        logger.info("Переходим на карточку события");
    }

    /**
     * В методе searchByKeyword() осуществляется поиск по ключевому слову
     */
    public void searchByKeyword() {
        wait.until(ExpectedConditions.elementToBeClickable(talksCard));
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(cfg.keyword());
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(talksCard).get(0)));
        logger.info("Осуществили поиск по ключевому слову");
    }
}
