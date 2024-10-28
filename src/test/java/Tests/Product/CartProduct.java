package Tests.Product;

import BaseClasses.TestBase;
import Pages.LoginPage.loginPage;
import Pages.ProductPage.productPage;
import org.testng.annotations.Test;


import java.lang.reflect.Method;

import static Utils.extentReports.ExtentTestManager.startTest;

public class CartProduct extends TestBase {


    loginPage logpage;
    productPage prdpage;


    public void intializer() {


        logpage = new loginPage(driver);
        prdpage = new productPage(driver);

    }


    @Test(priority = 1, description = "Verify that the user can cart and checkout single product")
    public void CartandCheckoutSingleProduct(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.loginFlow();
        prdpage.clickAddToCartButton("bike-light");
        prdpage.clickCartItemsButton();
        prdpage.clickCheckoutButton();
        prdpage.enterFirstName("Ademola");
        prdpage.enterLastName("Oyerinde");
        prdpage.enterPostalCode("1111");
        prdpage.clickContinueButton();
        prdpage.clickFinishButton();
        prdpage.clickBackHomeButton();
        logpage.clickLogoutButton();


    }

    @Test(priority = 2, description = "Verify that the user can cart and checkout multiple product")
    public void CartandCheckoutMultipleProduct(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.loginFlow();
        prdpage.clickAddToCartButton("bike-light");
        prdpage.clickAddToCartButton("backpack");
        prdpage.clickAddToCartButton("fleece-jacket");
        prdpage.clickAddToCartButton("bolt-t-shirt");
        prdpage.clickCartItemsButton();
        prdpage.clickCheckoutButton();
        prdpage.enterFirstName("Ademola");
        prdpage.enterLastName("Oyerinde");
        prdpage.enterPostalCode("1111");
        prdpage.clickContinueButton();
        prdpage.clickFinishButton();
        logpage.clickLogoutButton();
    }

    @Test(priority = 3, description = "Verify that the user can cart and checkout single product")
    public void CheckoutCartedProductwithempty_firstnamefield(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.loginFlow();
        prdpage.clickAddToCartButton("bolt-t-shirt");
        prdpage.clickCartItemsButton();
        prdpage.clickCheckoutButton();
        prdpage.enterLastName("Oyerinde");
        prdpage.enterPostalCode("1111");
        prdpage.clickContinueButton();
        prdpage.verifyCheckoutInfoErrorMessage("Error: First Name is required");
        logpage.clickLogoutButton();


    }

    @Test(priority = 4, description = "Verify that the user can cart and checkout single product")
    public void CheckoutCartedProductwithempty_lastnamefield(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.loginFlow();
        prdpage.clickAddToCartButton("bike-light");
        prdpage.clickCartItemsButton();
        prdpage.clickCheckoutButton();
        prdpage.enterFirstName("Ademola");
        prdpage.enterPostalCode("1111");
        prdpage.clickContinueButton();
        prdpage.verifyCheckoutInfoErrorMessage("Error: Last Name is required");
        logpage.clickLogoutButton();
    }

    @Test(priority = 5, description = "Verify that the user can cart and checkout single product")
    public void CheckoutCartedProductwithempty_Postalcodefield(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.loginFlow();
        prdpage.clickAddToCartButton("fleece-jacket");
        prdpage.clickCartItemsButton();
        prdpage.clickCheckoutButton();
        prdpage.enterFirstName("Ademola");
        prdpage.enterLastName("Oyerinde");
        prdpage.clickContinueButton();
        prdpage.verifyCheckoutInfoErrorMessage("Error: Postal Code is required");
        logpage.clickLogoutButton();
    }



}
