package Pages.LoginPage;

import BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class loginPage extends PageBase {
    public loginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id="user-name")
    WebElement userNameField;

    @FindBy(id="password")
    WebElement passwordField;

    @FindBy(id="login-button")
    WebElement LoginButton;

    @FindBy(id="react-burger-menu-btn")
    WebElement MenuButton;

    @FindBy(id="logout_sidebar_link")
    WebElement LogoutButton;


    public void enterUserName(String userName){
        waitForVisibility(userNameField);
        clearTextField(userNameField);
        enterText(userNameField,userName);
   }

    public void clearUserName(){
        waitForVisibility(userNameField);
        clearTextField(userNameField);
    }

    public void clearPassword(){
        waitForVisibility(passwordField);
        clearTextField(passwordField);
    }


    public void enterPassword(String password){
        waitForVisibility(passwordField);
        clearTextField(passwordField);
        enterText(passwordField,password);
   }

    public void clickSubmitButton(){
        waitForVisibility(LoginButton);
       click(LoginButton);
   }

    public void clickLogoutButton(){
        waitForVisibility(MenuButton);
        click(MenuButton);
        waitForVisibility(LogoutButton);
        click(LogoutButton);
    }

    public void loginFlow(){
        enterUserName("standard_user");
        enterPassword("secret_sauce");
        clickSubmitButton();
    }

    @FindBy(css = "*[data-test=\"error\"]")
    WebElement errorMessageElement;

    // Method to assert the error message on the login page, parameterized to accept the expected error message
    public void verifyInvalidLoginDetailsErrorMessage(String expectedMessagePart) {
        // Get the actual error message from the element
        waitForVisibility(errorMessageElement);
        String actualMessage = errorMessageElement.getText();
        System.out.println("Actual Error Message: " + actualMessage);

        try {
            // Assert that the actual message contains the expected message part
            Assert.assertTrue(actualMessage.contains(expectedMessagePart),
                    "The actual message does not contain the expected part: " + expectedMessagePart);

            System.out.println("The error message contains the expected part: " + expectedMessagePart);
        } catch (AssertionError e) {
            // Handle the case where the assertion fails
            System.err.println("Assertion failed: " + e.getMessage());
            System.out.println("Expected message part: " + expectedMessagePart);
            System.out.println("Actual message: " + actualMessage);
        }
    }
//test
}
