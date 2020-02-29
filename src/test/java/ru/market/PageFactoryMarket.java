package ru.market;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PageFactoryMarket {

    private WebDriver chromeWebDriver;
    private String url = "https://market.yandex.ru/";
    public List<String> listOfWebElement;


    @FindBy(how = How.XPATH, xpath = "//a[@href='/catalog--elektronika/54440']//span[1]")
    // //div[@class='_3Lwc_UVFq4']   раздел меню "Электроника"
            WebElement Electronic;

    @FindBy(how = How.XPATH, xpath = "(//a[contains(@class,'_2qvOOvezty _2x2zBaVN-3')])[1]")
    // (//a[contains(@class,'_2qvOOvezty _2x2zBaVN-3')])[1]  Раздел меню "Мобильные телефоны"
            WebElement mobilePhone;

    @FindBy(how = How.XPATH, xpath = "(//div[@class='LhMupC0dLR'])[1]")
    //(//div[@class='LhMupC0dLR'])[1]  флаг фильтра "Apple'
            WebElement appleManufactured;

    /*
    Через кнопку "Вперед" не вышло. Программа пытается обратиться к первоначальной кнопке, не смотря на новую страницу.

    @FindBy(how = How.XPATH, xpath = "//a[contains(@class,'button button_size_m')]")
        // //a[contains(@class,'n-pager__button-next')] кнопка "Вперед"
                WebElement buttonNext;
     */

    //кнопка "Показать все" как отдельный элемент не понадобилось. Не нашел как создавать WebElementWait использую готовый жлемент.

    @FindBy(how = How.XPATH, xpath = "//a[contains(@class,'button button_size_m')]")
// //a[contains(@class,'button button_size_m button_theme_pseudo')]   кнопка "Показать все"
            WebElement buttonShowAll;

    public PageFactoryMarket( WebDriver chromeDriver ) {
        this.chromeWebDriver = chromeDriver;
        chromeWebDriver.get(url);
    }

    public void appliFilter() {
        Electronic.click();
        mobilePhone.click();
        appleManufactured.click();
    }

    public void clicker() {
        while (true) {
            try {
                new WebDriverWait(chromeWebDriver, 3).until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//a[contains(@class,'button button_size_m')]"))).click();
            } catch (TimeoutException e) {
                break;
            }
        }
    }

    public void find() {
        List<String>stringList = new ArrayList<>();
        List<WebElement> tempList = chromeWebDriver.findElements(By.xpath("//div[@class='n-snippet-cell2__brand-name']"));
        if (tempList.size() > 0) {
            for (int i = 0; i < tempList.size(); i++) {
                String temp = tempList.get(i).getText();
                stringList.add(temp);
            }
            setListOfWebElement(stringList);
        }
    }


    public boolean checkManufacture( String nameBrand, List<String> list ) {
        if (list.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                if (!list.get(i).equals(nameBrand)) {
                    return false;
                }
            }
            return true;
        }
    }


    public List<String> getListOfWebElement() {
        return listOfWebElement;
    }

    public void setListOfWebElement( List<String> listOfWebElement ) {
        this.listOfWebElement = listOfWebElement;
    }
}
