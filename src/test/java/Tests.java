import org.junit.Test;
import org.openqa.selenium.support.PageFactory;


public class Tests extends SeleniumSettings{
    
    @Test
    public void checkingProductComparison(){
        MarketPage marketPage = PageFactory.initElements(driver, MarketPage.class);
        marketPage.goToMarket();
        marketPage.filteringByBrand();
        marketPage.sortingResults();
        marketPage.comparison();
    }

}
