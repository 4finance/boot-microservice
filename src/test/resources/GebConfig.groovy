import org.openqa.selenium.Dimension
import org.openqa.selenium.firefox.FirefoxDriver

baseUrl = "http://localhost:8095/swagger"
driver = {
    def firefoxDriver = new FirefoxDriver()
    firefoxDriver.manage().window().setSize(new Dimension(1600,1200))
    return firefoxDriver
}