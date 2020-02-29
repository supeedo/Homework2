package Setting;


import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSetting {
    public static WebDriver chromeDriver;

    @Before
    public void setupTest() {
        System.out.println("Before");
        System.setProperty("webdriver.chrome.driver", "/Users/ChesenFew/Documents/MyIdeaProject/Homework2/chromedriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
    }
    /*
    @After
    public void close() {
        System.out.println("After");
        chromeDriver.quit();
    }

     */
}
