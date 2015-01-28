import org.openqa.selenium.firefox.FirefoxDriver

baseUrl = "http://localhost:8095/swagger"
driver = {
    def firefoxDriver = new FirefoxDriver()
    firefoxDriver.manage().window().maximize()
    firefoxDriver
}