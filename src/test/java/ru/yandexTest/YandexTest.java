/*
Задача:
1.	запустить Chrome
2.	Открыть яндекс
3.	Ввести «Гладиолус». Нажать поиск
4.	Убедится что в полученной выборке на первой странице есть ссылка на википедию
 Автотест необходимо написать, используя данный стек:
Java, JUnit, Selenium
Автотест необходимо написать, используя данный стек:
Java, JUnit, Selenium, PageObject

 */
package ru.yandexTest;

import Setting.WebDriverSetting;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class YandexTest extends WebDriverSetting {

    @Test  // Задание 1.1
    public void testYandex() {
        System.out.println("testYandex");
        chromeDriver.get("http://yandex.ru");
        PageObject yandexPageObject = new PageObject(chromeDriver);
        yandexPageObject.find("Гладиолус");
        yandexPageObject.refreshList();
        boolean check = false;
        for (WebElement wb : yandexPageObject.getListOfWebElement()) {
            if (wb.getText().contains("ru.wikipedia.org")) {
                check = true;
            }
        }
        Assert.assertTrue(check);
    }


}
