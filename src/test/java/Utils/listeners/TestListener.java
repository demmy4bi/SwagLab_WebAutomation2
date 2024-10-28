package Utils.listeners;

import BaseClasses.TestBase;
import Utils.extentReports.ExtentManager;
import Utils.extentReports.ExtentTestManager;
import Utils.sendEmails.EmailUtils;
import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import Utils.ReportUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener extends TestBase implements ITestListener {
    private ExtentReports extent;
    private final TestBase testBase = new TestBase(); // Initialize TestBase properly
    private final EmailUtils emailUtils = new EmailUtils(testBase);
    private final Map<String, String> testResults = new HashMap<>();
    private final Map<String, String> useCaseResults = new HashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Called before each test method is invoked
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String category = ExtentTestManager.getCategory(testName);
       // String category = ExtentTestManager.getCategory(testName);
        testResults.put(testName, "pass");
        useCaseResults.put(testName, category);
        System.out.println("Test Passed: " + testName + " | Use Case: " + category);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String category = ExtentTestManager.getCategory(testName);
        testResults.put(testName, "fail");
        useCaseResults.put(testName, category);
        System.out.println("Test Failed: " + testName + " | Use Case: " + category);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String category = ExtentTestManager.getCategory(testName);
        testResults.put(testName, "skip");
        useCaseResults.put(testName, category);
        System.out.println("Test Skipped: " + testName + " | Use Case: " + category);

    }

    @Override
    public void onStart(ITestContext context) {
        // Called before any test method is run
        extent = ExtentManager.createExtentReports();
        String reportPath = ExtentManager.getReportPath();
    }


    @Override
    public void onFinish(ITestContext context) {
        //Here I want to use the context to read the status after test execution, create a table with
        //...the dynamic content and send as the body of the email, This is where the issue is.

        System.out.println("Test suite has finished. Generating report and sending email...");

        extent.flush();

        String reportPath = ExtentManager.getReportPath();
        File reportFile = new File(reportPath);

        if (!reportFile.exists()) {
            System.out.println("Report file not found: " + reportPath);
            return;
        }

        String body = ReportUtils.generateAndSendReport(testResults,useCaseResults, context);

        String to = "demmylee56@gmail.com";
        String subject = "TestNG Test Suite Report";

        try (PrintWriter out = new PrintWriter(new FileWriter("body.html"))) {
            out.println(body);
            System.out.println("body.html file has been created/updated with the report content.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to body.html: " + e.getMessage());
            e.printStackTrace();
        }

        emailUtils.sendEmail(to, subject, body, reportPath);

//                 Delete old reports after the current suite is finished
        File reportDirectory = new File("Reports");

        if (reportDirectory.exists() && reportDirectory.isDirectory()) {
            File[] files = reportDirectory.listFiles((dir, name) -> name.endsWith(".html"));
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && !file.getName().equals(new File(reportPath).getName())) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete file: " + file.getName());
                        }
                    }
                }
            }
        }

        System.out.println("Previous reports deleted. Suite is finished...");


    }

    public Map<String, String> getUseCaseResults() {
        return useCaseResults;
}
}
