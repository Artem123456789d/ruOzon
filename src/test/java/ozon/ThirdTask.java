package ozon;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class ThirdTask extends WebDriverSettings {

    private static String delNoDigOrLet (String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character .isLetterOrDigit(s.charAt(i)))
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public ThirdTask(WebDriver driver){
        this.driver = driver;
    }

    private final By headerLocator = By.cssSelector("header");
    private final By catalogLocator = By.cssSelector("button");
    private final  By appliancesLocator = By.cssSelector("[href=\"/category/bytovaya-tehnika-10500/\"]");
    private final  By spanLocator = By.cssSelector("span");
    private final  By kitchenLocator = By.cssSelector("[href=\"/category/tehnika-dlya-kuhni-10523/\"]");
    private final  By juisersLocator = By.cssSelector("[href=\"/category/sokovyzhimalki-10592/\"]");
    private final  By asideLocator = By.cssSelector("aside");
    private final  By comboboxLocator = By.cssSelector("[role=\"combobox\"]");
    private final  By divLocator = By.cssSelector("div");
    private final  By rangeFromLocator = By.cssSelector("input[qa-id=\"range-from\"]");
    private final  By rangeToLocator = By.cssSelector("input[qa-id=\"range-to\"]");
    private final  By filteringLocator = By.cssSelector("div[data-widget=\"searchResultsFiltersActive\"]");
    private final  By juicersListLocator = By.cssSelector("[style=\"width: 25%; max-width: 25%; flex: 0 0 25%;\"]");
    private final  By cartLocator = By.cssSelector("[data-widget=\"cart\"]");
    private  final By dotsLocator = By.cssSelector("[class=\"dots dots-blue\"]");
    private  final By stickyContainerLocator = By.xpath(".//*[@data-widget=\"stickyContainer\"]//span");
    private final  By clearSpaceLocator = By.cssSelector("[data-widget=\"catalogResultsHeader\"]");

    @Step("Переход на страницу соковыжималок")
    public void goToJuicers(){
        WebElement header = driver.findElement(headerLocator);
        header.findElement(catalogLocator).click();
        driver.findElement(appliancesLocator).click();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(asideLocator));
        WebElement kitchen = driver.findElement(kitchenLocator).findElement(By.xpath(".."));
        kitchen.findElement(spanLocator).click();
        kitchen.findElement(juisersLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(comboboxLocator));
    }

    @Step("Сортировка по цене")
    public void sortByPrice(){
        wait = new WebDriverWait(driver, 20);
        WebElement price = driver.findElement(divLocator);
        price.findElement(By.xpath(".."));
        price.findElement(rangeFromLocator).sendKeys(Keys.CONTROL, "a");
        price.findElement(rangeFromLocator).sendKeys("3000", Keys.ENTER);
        driver.findElement(clearSpaceLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dotsLocator));
        price.findElement(rangeToLocator).sendKeys(Keys.CONTROL, "a");
        price.findElement(rangeToLocator).sendKeys("4000", Keys.ENTER);
        driver.findElement(clearSpaceLocator).click();
        try {
            Thread.sleep(300);
        }
        catch (InterruptedException qq) {
            qq.printStackTrace();
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dotsLocator));
        driver.findElement(comboboxLocator).click();
        driver.findElement(comboboxLocator).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dotsLocator));
    }

    @Step("Проверка верности цены")
    public void checkPrice(){
        List<WebElement> juicersList = driver.findElements(juicersListLocator);

        for (int i = 1; i < juicersList.size(); i += 2)
        {
            int pric =  Integer.parseInt(delNoDigOrLet(juicersList.get(i).findElement(spanLocator).getText()));
            if (pric > 4000 || pric < 3000) System.out.println(false);
        }
    }


    @Step("Добавить соковыжималку в корзину")
    public void addToCart(){
        WebElement buttons = driver.findElement(By.xpath(".//*[@data-widget=\"megaPaginator\"]"));
        buttons.findElements(By.xpath(".//*[text() = \"В корзину\"]")).get(0).click();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException qq) {
            qq.printStackTrace();
        }
        driver.findElement(cartLocator).click();
    }


    @Step("Проверка увеличения суммы")
    public void checkPriceCart(){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(stickyContainerLocator));
        WebElement curprice = driver.findElements(stickyContainerLocator)
                .get(driver.findElements(stickyContainerLocator).size() - 1);
        int buf = Integer.parseInt(delNoDigOrLet(curprice.getText()));
        driver.findElement(comboboxLocator).click();
        driver.findElement(comboboxLocator)
                .sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.
                    textToBePresentInElement(curprice, curprice.getText())));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        int count = (Integer.parseInt(delNoDigOrLet(driver.findElement(comboboxLocator).
                findElement(By.xpath("..")).findElement(By.cssSelector("div")).getText())));
        int buf2 = (Integer.parseInt(delNoDigOrLet(curprice.getText()))) / count;
        System.out.println(buf == buf2);
    }
}
