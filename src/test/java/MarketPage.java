import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * В классе MarketPage реализована основная логика работы приложения:
 *      переход на сайт интернет-магазина,
 *      фильтрация товаров по производителям,
 *      сортировка результатов поиска,
 *      сравнение товаров
 */

public class MarketPage {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final Logger logger = LogManager.getLogger(Tests.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By suitableResults = By.xpath("//div[@data-zone-name= 'snippetList']");
    private final By confirmMessage = By.xpath("//div[@class = '_1_ABPFjOJQ']");
    private final By comparisonButton = By.xpath("//a[@href = '/my/compare-lists']");
    private final By comparisonList = By.xpath("//div[@data-tid= '412661c']");


    /**
     * В методе MarketPage происходит инициализация веб-драйвера,
     * а также настравается ожидание
     * @param driver веб-драйвер
     */
    public MarketPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }


    /**
     * В методе goToMarket осуществляется переход на сайт интернет-магазина
     */
    public void goToMarket(){
        driver.get(cfg.url());
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suitableResults));
        logger.info("Перешли на сайт Яндекс.Маркет - \"Электроника\"-> \"Смартфоны\"");
    }


    /**
     * В методе filteringByBrand осуществляется фильтрация товаров по производителям
     */
    public void filteringByBrand(){
        for (String brand : cfg.brands()) {
            By checkboxBrand = By.xpath("//input[@name= 'Производитель " + brand + "']/following-sibling::*");
            driver.findElement(checkboxBrand).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(suitableResults)));
            logger.info("Отфильтровали список товаров, добавив " + brand);
        }
    }


    /**
     * В методе sortingResults осуществляется сортировка товаров по выбранному критерию
     */
    public void sortingResults(){
        By sortingMethod = By.xpath("//button[contains( text(),'" + cfg.sortingMethod() + "')]");
        driver.findElement(sortingMethod).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(suitableResults)));
        logger.info("Отсортировали список товаров " + driver.findElement(sortingMethod).getText());
    }


    /**
     * В методе addToComparison описана логика добавления товаров
     * в список сравнения. Добавляется первый товар выбранного производителя.
     * Осуществляется проверка отображения плашки: "Товар {имя товара} добавлен к сравнению"
     */
    public void addToComparison(){
        for (String brand : cfg.brands()) {
            By comparison = By.xpath("//span[contains(text(),'Смартфон " + brand + "')]/ancestor::article//div[@class= '_2et7ag2eiP _3YfPcv73Wj _1rDffWmsUY']");
            By productName = By.xpath("//a[contains( @title,'Смартфон " + brand + "')]");
            driver.findElement(comparison).click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmMessage));
            Assert.assertEquals("Товар " + driver.findElement(productName).getAttribute("title") + " добавлен к сравнению", driver.findElement(confirmMessage).getText());
            logger.info("Добавили в список сравнения " + driver.findElement(productName).getAttribute("title"));
        }
    }


    /**
     * В методе comparison осуществляется переход на форму сравнения товаров.
     * Сравнивается количество выбранных производителей с количеством товаров,
     * добавленным в список сравнения
     */
    public void comparison(){
        addToComparison();
        driver.findElement(comparisonButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(comparisonList));
        Assert.assertEquals(driver.findElements(comparisonList).size(), cfg.brands().size());
        logger.info("Проверили количество товаров в списке сравниения");
    }

}
