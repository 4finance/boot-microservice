import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities


String smokeTestAppUrlProp = System.getProperty('smokeTestAppUrl')
if (smokeTestAppUrlProp) {
    baseUrl = smokeTestAppUrlProp
} else {
    //baseUrl in this file has precedence over -Dgeb.build.baseUrl, so set it only if system property is not defined
    if (!System.getProperty('geb.build.baseUrl')) {
        baseUrl = 'http://localhost:8095'
    }
}

driver = {
    WebDriver webDriver
    if (System.getProperty('webdriver') == 'chrome') {
        webDriver = configureForChrome()
    } else if (System.getProperty('webdriver') == 'gecko') {
        webDriver = configureForFirefox48orLater()
    } else {
        webDriver = configureForFirefox47_0_1orEarlier()
    }
    webDriver.manage().window().maximize()
    return webDriver
}

private static WebDriver configureForFirefox47_0_1orEarlier() {
    DesiredCapabilities capabilities = DesiredCapabilities.firefox()
    capabilities.setCapability(FirefoxDriver.MARIONETTE, false)
    return new FirefoxDriver(capabilities)
}

private static WebDriver configureForFirefox48orLater() {
    // Gecko driver can be downloaded from https://github.com/mozilla/geckodriver/releases
    // put Gecko driver on PATH or set property below
    // System.setProperty('webdriver.gecko.driver', '/usr/local/share/geckodriver')

    DesiredCapabilities capabilities = DesiredCapabilities.firefox()
    capabilities.setCapability(FirefoxDriver.MARIONETTE, true)
    return new FirefoxDriver(capabilities)
}

private static WebDriver configureForChrome() {
    // Chrome driver can be downloaded from https://sites.google.com/a/chromium.org/chromedriver
    // put Chrome driver on PATH or set property below
    // System.setProperty('webdriver.chrome.driver', '/usr/local/share/chromedriver')

    return new ChromeDriver()
}
