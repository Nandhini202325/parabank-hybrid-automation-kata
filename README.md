# ParaBank Hybrid Automation Framework

## Overview

This project demonstrates a hybrid test automation framework for ParaBank using UI and API automation.

The framework follows common automation design patterns and practices:

- Page Object Model (POM)
- Cucumber BDD
- Selenium WebDriver
- REST Assured
- TestNG
- Maven
- GitHub Actions CI/CD

## Tech Stack

| Component | Technology |
| --- | --- |
| Language | Java |
| UI Automation | Selenium WebDriver |
| API Automation | REST Assured |
| BDD | Cucumber |
| Test Runner | TestNG |
| Build Tool | Maven |
| Version Control | Git |
| CI/CD | GitHub Actions |

## Project Structure

```text
src/
  main/
    java/
    resources/
  test/
    java/
    resources/
```

## Reports and Screenshots

Run the full suite:

```bash
mvn clean test
```

Generated artifacts:

- Cucumber HTML report: `target/cucumber-reports/cucumber.html`
- Cucumber JSON report: `target/cucumber-reports/cucumber.json`
- Cucumber JUnit report: `target/cucumber-reports/cucumber.xml`
- Surefire/TestNG reports: `target/surefire-reports`
- Failure screenshots: `target/screenshots`

Screenshots are captured only for failed UI scenarios and are attached to the Cucumber HTML report.

## Running Tests by Tag

Run smoke tests:

```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

Run regression tests:

```bash
mvn clean test -Dcucumber.filter.tags="@regression"
```

Run API tests:

```bash
mvn clean test -Dcucumber.filter.tags="@api"
```

Run UI tests:

```bash
mvn clean test -Dcucumber.filter.tags="@ui"
```

Run smoke API tests:

```bash
mvn clean test -Dcucumber.filter.tags="@smoke and @api"
```

Run headless tag tests on Windows PowerShell:

```powershell
mvn clean test '-Dheadless=true' '-Dcucumber.filter.tags=@smoke'
```

## GitHub Actions CI/CD

The GitHub Actions workflow is defined in `.github/workflows/ci.yml`.

It runs on pushes and pull requests to `main`, and it can also be started manually from the Actions tab.

The workflow runs:

```bash
mvn clean test -Dheadless=true
```

The workflow uploads Cucumber reports, Surefire reports, and failed-step screenshots as build artifacts.

## Author

Nandhini
