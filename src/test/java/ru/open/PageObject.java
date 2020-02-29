package ru.open;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

class PageObject {

    private WebDriver chromeDriver;
    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> listOfWebelement;

    private WebElement dollarPurchanse;
    private WebElement dollarSale;
    private WebElement euroPurchanse;
    private WebElement euroSale;

    public PageObject( WebDriver chromeDriver ) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.xpath("//input[@class='gLFyf gsfi']")); // //input[@class='gLFyf gsfi']
        this.searchButton = chromeDriver.findElement(By.xpath("(//input[@name='btnK'])"));  //  (//input[@name='btnK'])[2]
    }

    public void search( String text ) {
        searchField.click();
        searchField.sendKeys(text);
        searchButton.click();
    }

    public void refreshList() {
        listOfWebelement = chromeDriver.findElements(By.xpath("//cite[contains(@class,'iUh30')]")); // (//cite[contains(@class,'iUh30')])
    }

    public List<WebElement> getListOfWebelement() {
        return listOfWebelement;
    }

    /*
    Сравнить курсы можно десятков различных способов с использованием массивов и прочее...
    Но решил сделать через отдельные методы на каждое значение.
     */
    public void setExchangeRatesFields() {
        this.dollarPurchanse = chromeDriver.findElement(By.xpath("(//span[@class='main-page-exchange__rate'])[1]"));
        // (//span[@class='main-page-exchange__rate'])[1]

        this.dollarSale = chromeDriver.findElement(By.xpath("(//span[@class='main-page-exchange__rate'])[2]"));
        // (//span[@class='main-page-exchange__rate'])[2]

        this.euroPurchanse = chromeDriver.findElement(By.xpath("(//span[@class='main-page-exchange__rate'])[3]"));
        // (//span[@class='main-page-exchange__rate'])[3]

        this.euroSale = chromeDriver.findElement(By.xpath("(//span[@class='main-page-exchange__rate'])[4] "));
        // (//span[@class='main-page-exchange__rate'])[4]
    }

    public double getDollarPurchanse() {
        // Можно использовать вместе replaсe - Locale.
        // Можно поставить проверку на null. Не знаю насколько это актуально.
        return Double.parseDouble(dollarPurchanse.getText().replace(',','.'));
    }

    public double getDollarSale() {
        return Double.parseDouble(dollarSale.getText().replace(',','.'));
    }

    public double getEuroPurchanse() {
        return Double.parseDouble(dollarPurchanse.getText().replace(',','.'));
    }

    public double getEuroSale() {
        return Double.parseDouble(dollarSale.getText().replace(',','.'));
    }
}

