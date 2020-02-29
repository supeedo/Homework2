package ru.yandexTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

class PageObject {
    private WebDriver chromeDriver;
    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> listOfWebElement;

    public PageObject( WebDriver chromeDriver ) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.xpath("//*[@id=\"text\"]")); // //*[@id="text"]
        this.searchButton = chromeDriver.findElement(By.xpath("//div[@class='search2__button']"));// //div[@class='search2__button']
    }

    public void find( String text ) {
        searchField.click();
        searchField.sendKeys(text);
        searchButton.click();
    }

    public void refreshList() {
        listOfWebElement = chromeDriver.findElements(By.xpath("//a[contains(@class,'link')]//b")); // //a[contains(@class,'link')]//b
    }

    public List<WebElement> getListOfWebElement() {
        return listOfWebElement;
    }
}
