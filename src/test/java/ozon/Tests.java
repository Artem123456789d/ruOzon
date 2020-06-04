package ozon;

import io.qameta.allure.Description;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class Tests extends WebDriverSettings {
    private final int code = 8466;
    private final String phone = "9198328533";

    @Test
    @Description(value = "Тест авторизуется на сайте с номером телефона " + phone + ". Проверяется, что кнопка" +
            "Войти сменилась на Мой профиль")
    public void First(){
        FirstTask firstTask = PageFactory.initElements(driver, FirstTask.class);
        steps.openMainPage();
        steps.makeScreenShot("Главная страница");
        firstTask.signUp(phone, code);
        firstTask.checkProfile();
        steps.makeScreenShot("Вошли в профиль");
    }

    @Test
    @Description(value = "Тест меняет город на Вольск, авторизуется на сайте и проверяет значение города")
    public void changeCity(){
        FirstTask firstTask = PageFactory.initElements(driver, FirstTask.class);
        SecondTask secondTask = PageFactory.initElements(driver, SecondTask.class);

        steps.openMainPage();
        steps.makeScreenShot("Главная страница");
        secondTask.changeCity();
        steps.makeScreenShot("Изменили город");
        firstTask.signUp(phone, code);
        firstTask.checkProfile();
        steps.makeScreenShot("Вошли в профиль");
        secondTask.checkCity();
    }

    @Test
    @Description(value = "Тест переходит в каталог Соковыжималки, выполняет поиск с диапазоном цен от 3000 до 4000." +
            "Проверяет что в результате отобразились только соковыжималки с ценами в этом диапазоне, выбирает " +
            "сортировку по цене. Добавляет в корзину вторую соковыжималку и переходит в неё. Далее проверяется, что " +
            "при увеличении количества товаров в 5 раз, цена увеличивается в 5 раз.")
    public void sumTest(){
        ThirdTask thirdTask = PageFactory.initElements(driver, ThirdTask.class);
        SecondTask secondTask = PageFactory.initElements(driver, SecondTask.class);
        steps.openMainPage();
        steps.makeScreenShot("Главная страница");
        secondTask.changeCity();
        steps.makeScreenShot("Изменили город");
        thirdTask.goToJuicers();
        steps.makeScreenShot("Открыли соковыжималки");
        thirdTask.sortByPrice();
        steps.makeScreenShot("Отсортировали по цене");
        thirdTask.checkPrice();
        thirdTask.addToCart();
        thirdTask.checkPriceCart();
        steps.makeScreenShot("Проверили цену в корзине ");
    }

    @Test
    @Description(value = "Тест переходит в каталог Соковыжималки, выполняет поиск с диапазоном цен от 3000 до 4000." +
            " Также выполняется поиск по соковыжималкам с мощностью >=1000 Вт " +
            "Проверяет что в результате отобразились только соковыжималки с ценами в этом диапазоне, выбирает " +
            "сортировку по цене. Добавляет в корзину вторую соковыжималку и переходит в неё. Далее проверяется, что " +
            "при увеличении количества товаров в 5 раз, цена увеличивается в 5 раз.")
    public void fourthTest(){
        SecondTask secondTask = PageFactory.initElements(driver, SecondTask.class);
        ThirdTask thirdTask = PageFactory.initElements(driver, ThirdTask.class);
        FourthTask fourthTask = PageFactory.initElements(driver, FourthTask.class);

        steps.openMainPage();
        steps.makeScreenShot("Главная страница");
        secondTask.changeCity();
        steps.makeScreenShot("Изменили город");
        thirdTask.goToJuicers();
        steps.makeScreenShot("Открыли соковыжималки");
        thirdTask.sortByPrice();
        steps.makeScreenShot("Отсортировали по цене");
        thirdTask.checkPrice();
        fourthTask.powerSorting();
        steps.makeScreenShot("Отсортировали по мощности");
        thirdTask.addToCart();
        thirdTask.checkPriceCart();
        steps.makeScreenShot("Проверили цену в корзине");
    }

}
