/*
1.	Запустить Chrome
2.	Открыть яндекс маркет
3.	Перейти в раздел мобильные телефоны
4.	Установить фильтр по производителю Apple
5.	Убедиться, что в полученной выборке телефоны только производителя Apple. Проверить все страницы. Скрипт должен просматривать все доступные страницы. Считаем что страниц может быть от одной до 10.
Автотест необходимо написать, используя данный стек:
Java, JUnit, Selenium, PageFactory
 */
package ru.market;

import Setting.WebDriverSetting;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;



public class MarketTest extends WebDriverSetting {

    @Test // Задание 1.3
    public void yandexMarketTest() {
        System.out.println("yandexMarketTest");
        PageFactoryMarket market = PageFactory.initElements(chromeDriver, PageFactoryMarket.class);
        market.appliFilter();
        market.clicker();
        market.find();
        System.out.println(market.getListOfWebElement().size());
        boolean check = market.checkManufacture("APPLE", market.listOfWebElement);
        Assert.assertTrue(check);
    }
}
