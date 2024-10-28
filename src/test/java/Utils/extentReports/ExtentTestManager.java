package Utils.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static Map<String, String> testCategoryMap = new HashMap<>();
    private static ExtentReports extent = ExtentManager.createExtentReports();

    public static synchronized ExtentTest startTest(String name, String desc, String category) {
        ExtentTest test = extent.createTest(name, desc).assignCategory(category);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
        testCategoryMap.put(name, category);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    public static synchronized String getCategory(String testName) {
        return testCategoryMap.get(testName);
}


}