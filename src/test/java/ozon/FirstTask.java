package ozon;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class FirstTask extends WebDriverSettings {
//    private WebDriver driver;
//    private WebDriverWait wait;

    public FirstTask(WebDriver driver){
        this.driver = driver;
    }

    private final  By profileMenuAnonymous = By.cssSelector("[data-widget=\"profileMenuAnonymous\"]");
    private final  By phoneLocator = By.cssSelector("input[name=\"phone\"]");
    //private final  By modalContainer = By.cssSelector("div[data-test-id=\"modal-container\"]");
    private final  By buttonSubmit = By.cssSelector("button[type=\"submit\"");
    private final  By inputCodeLocator = By.cssSelector("input[type=\"number\"]");
    private final  By profileLocator = By.cssSelector("[data-widget=\"profile\"]");


    @Step("Вход в учетную запись")
    public void signUp(String phone, int num){
        driver.findElement(profileMenuAnonymous).click();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("authFrame")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(phoneLocator));
        wait = new WebDriverWait(driver, 120);
        //driver.findElement(phoneLocator).sendKeys(phone);
        //WebElement header = driver.findElement(modalContainer);
        //driver.findElement(buttonSubmit).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(phoneLocator));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputCodeLocator));
        //List<WebElement> codes = header.findElements(inputCodeLocator); //для автоматизированного входа
        wait.until(ExpectedConditions.invisibilityOfElementLocated(inputCodeLocator));
/*        try {
            Thread.sleep(5000);                     //для автоматизированного входа
        }
        catch (InterruptedException qq) {
            qq.printStackTrace();
        }

        for (int i = 3; i >= 0; i = i - 1){
            String s = String.valueOf(num % 10);
            num = num / 10;
            codes.get(i).sendKeys(s);
        }*/
        driver.switchTo().defaultContent();
    }

    @Step("Проверка смены кнопки")
    public void checkProfile(){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(profileLocator));
        Assert.assertNotNull("Кнопка \"Войти в аккаунт\" сменилась на \"Мой профиль\"",
                driver.findElement(profileLocator));
        /*try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(profileLocator));
            System.out.println("Кнопка сменилась");
        }
        catch (Exception e){
            System.out.println("Кнопка не сменилась");
        }*/
    }
}
