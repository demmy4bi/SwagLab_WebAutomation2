package Tests.Login;

import BaseClasses.TestBase;
import Pages.LoginPage.loginPage;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static Utils.extentReports.ExtentTestManager.startTest;

public class ValidateLogin  extends TestBase {

    loginPage logpage;

    public void intializer() {


        logpage = new loginPage(driver);

    }

    @Test(priority = 1, description = "Verify that the user can login with valid credentials successfully")
    public void LoginwithValidCredentials(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.enterUserName("standard_user");
        logpage.enterPassword("secret_sauce");
        logpage.clickSubmitButton();
        logpage.clickLogoutButton();


    }

    @Test(priority = 2, description = "Verify that the user can not login with Invalid password")
    public void LoginwithInvalidPassword(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.enterUserName("standard_user");
        logpage.enterPassword("secret_sce");
        logpage.clickSubmitButton();
        logpage.verifyInvalidLoginDetailsErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 3, description = "Verify that the user can not login with Invalid username")
    public void LoginwithInvalidUsername(Method method) {

        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Product");
        intializer();
        logpage.enterUserName("standard_");
        logpage.enterPassword("secret_sauce");
        logpage.clickSubmitButton();
        logpage.verifyInvalidLoginDetailsErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }


//test
}
