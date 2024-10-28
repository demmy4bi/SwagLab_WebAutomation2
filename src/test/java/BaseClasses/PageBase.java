package BaseClasses;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PageBase {
   protected WebDriver driver;
    WebDriverWait wait;

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitForVisibility(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForClickAbility(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void sleep(int seconds) {
        try{
            Thread.sleep(seconds * 1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void click(WebElement element) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForClickAbility(element);
                element.click();
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }

    public void verifyText(WebElement element, String text,String comments){
        int attempts = 0;

        while (attempts < 3) {
            try {
                Assert.assertEquals(element.getText(),text,comments);

                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }




    public void scrollToElement(WebElement element) {

        int attempts = 0;

        while (attempts < 3) {
            try {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
            if (attempts == 3) {
                throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
            }

        }
        sleep(3);
    }

    public void enterText(WebElement element, String text) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForVisibility(element);
                element.sendKeys(text);
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to enter text after 3 attempts due to StaleElementReferenceException");
        }
    }


    public void searchText(WebElement element, String text) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                waitForVisibility(element);
                element.sendKeys(text);
                element.sendKeys(Keys.ENTER);
                break; // Exit loop if action is successful
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(driver, this);
                attempts++;
            }
        }

        if (attempts == 3) {
            throw new RuntimeException("Failed to search text after 3 attempts due to StaleElementReferenceException");
        }
    }





    public void checkLabelText(WebElement element, String text) {
        waitForVisibility(element);
        String labelText = element.getText();
        if(labelText.equals(text))
            System.out.println(text + "Label displayed");
        else
            System.out.println(text + "Label not displayed");
    }
    public void selectOptionByName(WebElement selectElement, String text) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(text);
    }
    public void waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static void clearTextField(WebElement element) {
        element.clear();
    }

}
