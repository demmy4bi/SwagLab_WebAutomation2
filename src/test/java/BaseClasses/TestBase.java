package BaseClasses;


import Utils.ReportUtils;
import Utils.extentReports.ExtentManager;
import Utils.logs.Log;
import Utils.sendEmails.EmailUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestBase extends TestListenerAdapter {

    String testDataFile_path = "src/test/java/Utils/testData.properties";
    FileInputStream fis;
    public Properties testData;
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;
    EdgeOptions edgeOptions;
    public WebDriver driver;

    protected ExtentReports extent;
    protected ExtentTest test;

    private final EmailUtils emailUtils = new EmailUtils(this);
    private final Map<String, String> testResults = new HashMap<>();

    public TestBase() {
        loadPropFile();
    }

    public void loadPropFile() {
        testData = new Properties();
        try {
            fis = new FileInputStream(testDataFile_path);
            testData.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTestData(String key){
        return testData.getProperty(key);
    }



    @BeforeClass
    public void mainSetup() {
        String browser = testData.getProperty("browser");

        if (browser.equals("chrome")) {
            chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equals("edge")) {
            edgeOptions = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.get(testData.getProperty("baseURL"));
        driver.manage().window().maximize();

        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        extent.attachReporter(sparkReporter);
    }

    public ExtentTest createTest(String testName, String description, String s) {
        return extent.createTest(testName, description);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");
    }

    public void selectOption(WebElement selectElement, int optionNumber) {
        Select select = new Select(selectElement);
        select.selectByIndex(optionNumber);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOnImplicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void turnOffImplicitWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static String generateRandomLetters(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return sb.toString();
    }

    public static String generateRandomNumbers(int length) {
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            sb.append(digits.charAt(index));
        }

        return sb.toString();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
       // onFinish(null); // Manually trigger onFinish for email
    }

//    @Override
//    public void onFinish(ITestContext context) {
//        Log.info("Process is onFinish method" + (context != null ? context.getName() : "no context"));
//        extentReports.flush();
//
//        System.out.println("Test suite has finished. Generating report and sending email...");
//
//        String reportPath = ExtentManager.getReportPath();
//        File reportFile = new File(reportPath);
//
//        if (!reportFile.exists()) {
//            System.out.println("Report file not found: " + reportPath);
//            return;
//        }
//
//        String body = ReportUtils.generateAndSendReport(testResults, context);
//        String to = "demmylee56@gmail.com";
//        String subject = "TestNG Test Suite Report";
//
//        emailUtils.sendEmail(to, subject, body, reportPath);
//    }
}







//extent.flush();

//        EmailUtil.sendEmailWithAttachment(
//                "demmylee56@gmail.com",
//                "demmy4bi@gmail.com",
//                "smtp.gmail.com",
//                "Test Report",
//                "Please find the attached test report.",
//                System.getProperty("user.dir") + "/test-output/ExtentReport.html"
//        );







//    @AfterClass
//    public void tearDown() {
//        if (null != driver) {
//            driver.quit();
//        }
//
//        extent.flush();
//
//        EmailUtil.sendEmailWithAttachment(
//                "demmylee56@gmail.com",
//                "demmy4bi@gmail.com",
//                "smtp.gmail.com",
//                "Test Report",
//                "Please find the attached test report.",
//                System.getProperty("user.dir") + "/test-output/ExtentReport.html"
//        );
//    }


