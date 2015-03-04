import org.openqa.selenium.Dimension
import org.openqa.selenium.firefox.FirefoxDriver

//baseUrl in this file has precedence over -Dgeb.build.baseUrl, so set it only if system property is not defined
if (!System.getProperty("geb.build.baseUrl")) {
    baseUrl="http://localhost:8095"
}

driver = {
    def firefoxDriver = new FirefoxDriver()
    firefoxDriver.manage().window().setSize(new Dimension(1600,1200))
    return firefoxDriver
}
