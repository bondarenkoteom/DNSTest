package dns;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {
    private static WebDriver driver;


    public static void initWebDriver() {
        Properties properties = PropertiesSettings.getInstance().getProperties();
        String browser = properties.getProperty("browser", "firefox");
        switch (browser) {
            case "chrome":
                System.setProperty(properties.getProperty("chromeDriver"), properties.getProperty("driverPathChrome"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty(properties.getProperty("firefoxDriver"), properties.getProperty("driverPathFirefox"));
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(properties.getProperty("urlDns"));
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
