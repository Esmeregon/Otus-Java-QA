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

    @Given("We went to the website")
    public void weWentToTheWebsite() {
        coursesPage.goToOtus();
    }

    @When("We go through authorization on the site")
    public void weGoThroughAuthorizationOnTheSite() {
        coursesPage.goToSignInPage();
        signInPage.logIn(cfg.email(), cfg.password());
    }

    @And("go to the personal account page")
    public void goToThePersonalAccountPage() {
        coursesPage.goToAccountSettings();
    }

    @Then("We have checked the title of the personal account page")
    public void weHaveCheckedTheTitleOfThePersonalAccountPage() {
        Assert.assertEquals( "Личный кабинет", accountPage.checkedPageTitle());
    }
}
