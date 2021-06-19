package demo.steps;

import demo.utils.SeleniumSettings;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class MyStepdefsParamitrized extends SeleniumSettings {

    @Before
    public void initialization(){
        startUp();
    }

    @After
    public void teardown(){
        end();
    }

    @Given("The user went to the login page")
    public void theUserWentToTheLoginPage() {
        coursesPage.goToOtus();
        coursesPage.goToSignInPage();
    }

    @When("User enters incorrect email {string} or password {string}")
    public void userEntersIncorrectEmailOrPassword(String email, String password) {
        signInPage.logIn(email, password);
    }

    @Then("An error message is displayed")
    public void anErrorMessageIsDisplayed() {
        Assert.assertEquals("Такая пара логин/пароль не существует", signInPage.getErrorAuthorizationMessage());
    }
}
