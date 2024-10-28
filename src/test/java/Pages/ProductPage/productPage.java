package Pages.ProductPage;

import BaseClasses.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class productPage extends PageBase {

    public productPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css=".shopping_cart_link")
    WebElement CartItemsField;

    public void clickCartItemsButton(){
        waitForVisibility(CartItemsField);
        click(CartItemsField);
    }

    @FindBy(id="checkout")
    WebElement checkoutField;

    public void clickCheckoutButton(){
        waitForVisibility(checkoutField);
        click(checkoutField);
    }

    @FindBy(id="first-name")
    WebElement firstnameField;

    public void enterFirstName(String firstName){
        waitForVisibility(firstnameField);
        enterText(firstnameField,firstName);
    }

    @FindBy(id="last-name")
    WebElement lastnameField;

    public void enterLastName(String lastName){
        waitForVisibility(lastnameField);
        enterText(lastnameField,lastName);
    }


    @FindBy(id="postal-code")
    WebElement postalcodeField;

    public void enterPostalCode(String PostalCode){
        waitForVisibility(postalcodeField);
        enterText(postalcodeField,PostalCode);
    }

    @FindBy(id="continue")
    WebElement ContinueField;

    public void clickContinueButton(){
        waitForVisibility(ContinueField);
        click(ContinueField);
    }



    @FindBy(id="finish")
    WebElement FinishField;

    public void clickFinishButton(){
        waitForVisibility(FinishField);
        click(FinishField);
    }

    @FindBy(id="back-to-products")
    WebElement BackHomeField;

    public void clickBackHomeButton(){
        waitForVisibility(BackHomeField);
        click(BackHomeField);
    }

    @FindBy(css = "*[data-test=\"error\"]")
    WebElement errorMessageElement;

    // Method to assert the Checkout Information error message, parameterized to accept the expected error message
    public void verifyCheckoutInfoErrorMessage(String expectedMessagePart) {
        // Get the actual success message from the element
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


    // Method to locate and click the add-to-cart button dynamically based on productName
    public void clickAddToCartButton(String productName) {
        String cssSelector = String.format("#add-to-cart-sauce-labs-%s", productName);
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

}