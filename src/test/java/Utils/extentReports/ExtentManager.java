package Utils.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportPath;

    private static String localDate() {
        DateTimeFormatter dateFomart = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime curTime = LocalDateTime.now();
        return dateFomart.format(curTime);
    }


    public synchronized static ExtentReports createExtentReports() {
        if (extent == null) {
            extent = new ExtentReports();
            String currentDate = localDate();
            reportPath = "./Reports/Skeleton-" + currentDate + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Test Report");
            reporter.config().setReportName("SauceDemo Report");
            extent.attachReporter(reporter);
            extent.setSystemInfo("Author", "Chudy Okoli");
            extent.setSystemInfo("Platform", "Chrome, Firefox, and Edge");
        }
        return extent;
    }


    public static String getReportPath() {
        return reportPath;
}



}