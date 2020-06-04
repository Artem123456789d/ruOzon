package ozon;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FourthTask extends WebDriverSettings {

    public FourthTask(WebDriver driver){
        this.driver = driver;
    }

    private final By powerLocator = By.xpath(".//*[text() = \"\n" +
            "    Мощность, Вт\n" +
            "  \"]");
    private final By rangeFromLocator = By.cssSelector("input[qa-id=\"range-from\"]");
    private final By clearSpaceLocator = By.cssSelector("div[data-widget=\"catalogResultsHeader\"]");
    private final By dotsLocator = By.cssSelector("[class=\"dots dots-blue\"]");

    @Step("Сортировка по мощности")
    public void powerSorting(){
        wait = new WebDriverWait(driver, 20);
        WebElement power = driver.findElement(powerLocator).findElement(By.xpath(".."));
        power.findElement(rangeFromLocator).sendKeys(Keys.CONTROL, "a");
        power.findElement(rangeFromLocator).sendKeys("1000");
        driver.findElement(clearSpaceLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dotsLocator));
    }
}
