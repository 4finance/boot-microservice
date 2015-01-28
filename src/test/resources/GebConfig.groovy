import org.openqa.selenium.firefox.FirefoxDriver

baseUrl = "http://localhost:8095/swagger"
driver = {
    def firefoxDriver = new FirefoxDriver()
    SharedResources.instance.browser = firefoxDriver
    firefoxDriver.manage().window().maximize()
    firefoxDriver
}