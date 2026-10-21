# BrowserStack Playwright demo

This project runs a Playwright horizontal-slider test on BrowserStack.

Set your BrowserStack credentials in the environment; never put them in source files:

```powershell
$env:BROWSERSTACK_USERNAME = "your-browserstack-username"
$env:BROWSERSTACK_ACCESS_KEY = "your-browserstack-access-key"
mvn test
```

In IntelliJ IDEA, open **Run > Edit Configurations**, select the TestNG configuration for `testng.xml`, and add these under **Environment variables**:

```text
BROWSERSTACK_USERNAME=your-browserstack-username
BROWSERSTACK_ACCESS_KEY=your-browserstack-access-key
```

Alternatively, add `-DBROWSERSTACK_USERNAME=... -DBROWSERSTACK_ACCESS_KEY=...` to the run configuration's **VM options**. The test fails during setup with a clear error if either credential is missing.

The browser and operating-system capabilities are configured in `testng.xml`. The suite currently targets Chrome on macOS Monterey.
