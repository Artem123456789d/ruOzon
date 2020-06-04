package ozon;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverSettings {
    public WebDriver driver;
    public WebDriverWait wait;
    public WebDriverSteps steps;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        steps = new WebDriverSteps(driver);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();


    }



    @After
    public void close(){
        driver.quit();
    }
}
