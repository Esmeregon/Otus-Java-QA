package demo.steps;

import demo.utils.SeleniumSettings;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class MyStepdefs extends SeleniumSettings{

    @Before
    public void initialization(){
        startUp();
    }

    @After
    public void teardown(){
        end();
    }

    @Given("The user went to the website")
    public void theUserWentToTheWebsite() {
        coursesPage.goToOtus();
    }

    @When("The user go through authorization on the site")
    public void theUserGoThroughAuthorizationOnTheSite() {
        coursesPage.goToSignInPage();
        signInPage.logIn(cfg.email(), cfg.password());
    }

    @And("go to the personal account page")
    public void goToThePersonalAccountPage() {
        coursesPage.goToAccountSettings();
    }

    @Then("The system should display the page title")
    public void theSystemShouldDisplayThePageTitle () {
        Assert.assertEquals( "Личный кабинет", accountPage.checkedPageTitle());
    }
}
