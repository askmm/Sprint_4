package ru.yandexqa.week4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ScooterOrder {
    private WebDriver driver;

    //////////// order first page
    private By personalDataFields = By.xpath(".//input[@class = 'Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private By metroStationField = By.xpath(".//input[@class='select-search__input']");
    private By metroStationItem = By.xpath(".//li[@class='select-search__row']");
    private By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']//button[text() = 'Далее']");

    //////////// order second page
    private By rentBeginDateField = By.xpath(".//div[@class = 'react-datepicker__input-container']//input");
    private By rentDurationField = By.className("Dropdown-control");
    private By rentDurationValues = By.className("Dropdown-option");
    private final String colorXpathPattern = ".//input[@id = '$color$']/parent::label";
    private By orderComment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    private By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']//button[text() = 'Заказать']");

    //////////// pop-up confirmation
    private By confirmButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']//button[text() = 'Да']");

    //////////// successful order
    private By orderPlaced = By.xpath(".//div[@class = 'Order_Text__2broi' and contains(text(), 'Номер заказа:')]");

    public ScooterOrder(WebDriver driver) {
        this.driver = driver;
    }

    public ScooterOrder fillDataFirstPage(String[] personalData, int metroStationIndex){
        ScooterCookies cookies = new ScooterCookies(driver);
        cookies.confirmCookieButtonClick();
        List<WebElement> fields = driver.findElements(personalDataFields);
        for (int i = 0; i < 4; i++) {
            fields.get(i).sendKeys(personalData[i]);
        }
        metroStationClickAndChoose(metroStationIndex);
        return this;
    }

    public ScooterOrder fillDataSecondPage(String[] rentData, int rentDurationIndex){
        chooseRentBeginDate(rentData[0]);
        chooseRentDuration(rentDurationIndex);
        chooseScooterColor(rentData[1]);
        driver.findElement(orderComment).sendKeys(rentData[2]);
        return this;
    }

    public ScooterOrder nextButtonClick(){
        driver.findElement(nextButton).click();
        return this;
    }

    private void metroStationClickAndChoose(int index){
        driver.findElement(metroStationField).click();
        WebElement stationName = driver.findElements(metroStationItem).get(index);
        stationName.click();
    }

    public ScooterOrder orderButtonClick(){
        driver.findElement(orderButton).click();
        return this;
    }

    public ScooterOrder confirmButtonClick(){
        driver.findElement(confirmButton).click();
        return this;
    }

    private void chooseRentBeginDate(String date){
        driver.findElement(rentBeginDateField).click();
        ScooterOrderCalendar calendar = new ScooterOrderCalendar(driver);
        calendar.chooseDate(date);

    }
    private void chooseScooterColor(String color){
        String colorXpath = colorXpathPattern.replace("$color$", color);
        driver.findElement(By.xpath(colorXpath)).click();
    }

    private void chooseRentDuration(int index){
        driver.findElement(rentDurationField).click();
        WebElement durationItem = driver.findElements(rentDurationValues).get(index);
        durationItem.click();
    }

    public boolean isOrderNumberVisible(){
        return driver.findElement(orderPlaced).isDisplayed();
    }

}
