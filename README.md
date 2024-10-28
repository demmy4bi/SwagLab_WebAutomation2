# SwagLab_WebAutomation2

## Task
This project contains automated tests for the login functionality, carting, and checkout processes of the Sauce Demo platform, implemented using Selenium Java with a Page Object Model (POM) framework.

### Key Features
- **Comprehensive Test Coverage**: Both valid and invalid scenarios are included.
- **Login Automation**:
    - Scripts for successful login using valid credentials.
    - Scripts for failed login attempts using invalid usernames and passwords.
- **Carting and Checkout Automation**:
    - Automation for adding single and multiple products to the cart.
    - Completion of the checkout process for these products.
- **Assertions**:
    - Assertions to handle invalid input scenarios, such as incorrect login credentials and errors on the checkout information page.
    - Expected errors are captured in the assertion methods.
- **Reporting**:
    - Automated report generation.
    - Reports are sent to stakeholders via email.
- **CI/CD Integration**:
    - The automated test scripts are integrated into the CI/CD pipeline using GitHub Actions.

## Instructions to Run the Script
1. Open the project.
2. Navigate to the `testNg.xml` file, where the test scripts are implemented along with the required listeners.
3. Right-click on the `testNg.xml` file and select **Run**.
