package ozon;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.util.List;

public class SecondTask extends WebDriverSettings {
    public SecondTask(WebDriver driver){
        this.driver = driver;
    }

    private final  By navigationLocator = By.cssSelector("div[role=\"navigation\"]");
    private final  By modalContentLocator = By.cssSelector("[class=\"modal-content\"]");
    private final  By cityLocator = By.cssSelector("span");

    @Step("Изменение города на Вольск")
    public void changeCity(){
        wait = new WebDriverWait(driver, 20);

        WebElement header = driver.findElement(navigationLocator);
        String currentCity = header.findElement(cityLocator).getText();
        header.findElement(By.cssSelector("button")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(modalContentLocator));
        WebElement container = driver.findElement(modalContentLocator);
        container.findElement(By.cssSelector("input")).sendKeys("Вольск");

        try {
            Thread.sleep(400);
        }
        catch (InterruptedException qq) {
            qq.printStackTrace();
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("circle")));

        List<WebElement> cities = container.findElements(By.cssSelector("a"));

        for (WebElement city: cities) {
            if (city.getText().equals("Вольск, Саратовская область")) {
                city.click();
                break;
            }
        }

        wait.until(ExpectedConditions.textToBePresentInElement(header.findElement(cityLocator), "Вольск"));

        //System.out.println(!currentCity.equals(header.findElement(cityLocator).getText()));

    }

    @Step("Проверка смены города на Вольск")
    public void checkCity(){
        Assert.assertTrue("Город сменился",driver.findElement(navigationLocator).
                findElement(cityLocator).getText().equals("Вольск") );
        //System.out.println(driver.findElement(navigationLocator).findElement(cityLocator).getText().equals("Вольск"));
    }
}
