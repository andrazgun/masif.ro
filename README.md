### Description

The main scope is the framework structure rather than actual testing, therefore the number of tests in the feature files
is small.

---

**Introduction**  
**Test automation framework** for the e-commerce website **www.masif.ro** to ensure the stability, performance, and reliability of its functionalities. The framework was designed with scalability, maintainability, and comprehensive reporting in mind.

---

**Technology Stack**  
The framework leverages a combination of modern tools and technologies for efficient test development and execution:
- **Programming Language**: Java
- **Browser Automation**: Selenium WebDriver --> https://www.selenium.dev/documentation/webdriver/
- **Dependency Management**: WebDriver Manager by Boni García --> https://bonigarcia.dev/webdrivermanager/
- **Test Execution Framework**: TestNG --> https://testng.org/
- **Reporting**: Allure Report --> https://allurereport.org/
- **Logging**: log4j --> https://logging.apache.org/log4j/2.x/index.html

---

**XML Test Suite Design**  
Test suites tailored to different levels of testing:
1. **Smoke Suite**: A lightweight suite covering critical functionalities to ensure basic application health.
2. **Regression Suite**: A comprehensive suite designed to verify existing features remain unaffected by new updates or fixes.

---

**Data-Driven Testing**  
To ensure flexibility and coverage, the framework supports data-driven testing using:
- **JSON**: For quick and easily maintainable test data files.
- **MySQL**: For managing complex, relational test data dynamically.

---

**Key Features of the Framework**  
- **Dynamic Browser Management**: WebDriver Manager automates driver handling, ensuring compatibility with the latest browser versions.
- **Parallel Execution**: TestNG enables parallel test execution, reducing test run time and increasing efficiency.
- **Enhanced Logging**: log4j provides detailed logs for debugging and traceability during test runs.
- **Advanced Reporting**: Allure Reports offer visually rich, interactive reports with test case status, logs, and screenshots for failed tests.

---
**Test Results**

- Run in CLI "mvn clean test" or manually by right-clicking on any of the xml suites and selecting Run
- Reports are generated in target/allure-reports
- To view the report run in CLI "allure serve .\target\allure-results\"

