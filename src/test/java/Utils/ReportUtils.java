package Utils;

import org.testng.ITestContext;

import java.util.Map;


public class ReportUtils {

    public static String generateAndSendReport(Map<String, String> testResults, Map<String, String> useCaseResults, ITestContext context) {


        // Start building the HTML content
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        htmlContent.append("<p>Dear all,<br></p>");
        htmlContent.append("<p>Please find the attached test report for Sauce Demo App.<br>The table below shows the details of the executed tests and their statuses</p>");

        // First Table: Test Summary
        htmlContent.append("<h2>Test Summary</h2>");
        htmlContent.append("<table border='1' style='border-collapse: collapse;'>");
        htmlContent.append("<tr>")
                .append("<th>Total Tests</th>")
                .append("<th>Passed</th>")
                .append("<th>Failed</th>")
                .append("<th>Skipped</th>")
                .append("</tr>");

        int passed = 0, failed = 0, skipped = 0;
        for (Map.Entry<String, String> entry : testResults.entrySet()) {
            String status = entry.getValue();
            switch (status) {
                case "pass":
                    passed++;
                    break;
                case "fail":
                    failed++;
                    break;
                case "skip":
                    skipped++;
                    break;
            }
        }

        int totalTests = testResults.size();
        htmlContent.append("<tr>")
                .append("<td>").append(totalTests).append("</td>")
                .append("<td>").append(passed).append("</td>")
                .append("<td>").append(failed).append("</td>")
                .append("<td>").append(skipped).append("</td>")
                .append("</tr>");
        htmlContent.append("</table>");

        // Second Table: Detailed Results
        htmlContent.append("<h2>Detailed Results</h2>");
        htmlContent.append("<table border='1' style='border-collapse: collapse;'>");
        htmlContent.append("<tr>")
                .append("<th>Suite</th>")
                .append("<th>Use Cases</th>")
                .append("<th>Test Cases</th>")
                .append("<th>Status</th>")
                .append("</tr>");

        for (Map.Entry<String, String> entry : testResults.entrySet()) {
            String testName = entry.getKey();
            String status = entry.getValue();
            String useCase = useCaseResults.get(testName);

            String bgColor;
            switch (status) {
                case "pass":
                    bgColor = "#b1ff3d";
                    break;
                case "fail":
                    bgColor = "#E8A4A4";
                    break;
                case "skip":
                    bgColor = "#FFFFE0";
                    break;
                default:
                    bgColor = "white"; // Default color if status is unknown
                    break;
            }

            htmlContent.append("<tr style='background-color:").append(bgColor).append(";'>")
                    .append("<td>").append(context.getSuite().getName()).append("</td>")
                    .append("<td>").append(useCase).append("</td>")
                    .append("<td>").append(testName).append("</td>")
                    .append("<td>").append(status).append("</td>")
                    .append("</tr>");
        }

        htmlContent.append("</table>");
        htmlContent.append("<p>Note - This email is automated and not monitored, please do not reply. For inquiries, you can reach out to the Test Automation Team</p>");
        htmlContent.append("<p>Regards,<br>Automation Team</p>");

        htmlContent.append("</body></html>");

        return htmlContent.toString();


}
}
