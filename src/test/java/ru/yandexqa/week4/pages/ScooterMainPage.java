package ru.yandexqa.week4.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandexqa.week4.config.Config.CHECK_ORDER_URL;
import static ru.yandexqa.week4.config.Config.ROOT_URL;

public class ScooterMainPage {
    private WebDriver driver;
    private By faqElementsQuestion = By.xpath(".//div[@class='Home_FAQ__3uVm4']//div[@class='accordion__item']");
    private By faqElementsAnswer = By.xpath(".//div[@class='Home_FAQ__3uVm4']//div[@class='accordion__panel']");
    private By orderTopButton = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text() = 'Заказать']");
    private By orderBottomButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button[text() = 'Заказать']");

    private By orderStatusButton = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[text() = 'Статус заказа']");
    private By goButton = By.xpath(".//div[@class='Header_SearchInput__3YRIQ']//button[text() = 'Go!']");
    private By orderNumberField = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input");
    private By errorPicture = By.className("Track_NotFound__6oaoY");

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
        driver.get(ROOT_URL);
    }

    public ScooterOrder orderButtonClick(String buttonPosition){
        ScooterCookies scooterCookies = new ScooterCookies(driver);
        scooterCookies.confirmCookieButtonClick();
        By button = buttonPosition.equals("top") ? orderTopButton : orderBottomButton;
        driver.findElement(button).click();
        ScooterOrder scooterOrderPage = new ScooterOrder(driver);
        return scooterOrderPage;
    }

    public ScooterMainPage cookiesConfirmButtonClick(){
        ScooterCookies cookies = new ScooterCookies(driver);
        cookies.confirmCookieButtonClick();
        return this;
    }

    public ScooterMainPage faqQuestionClick(int index){
        WebElement question = driver.findElements(faqElementsQuestion).get(index);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", question);
        new WebDriverWait(driver, 25).until(ExpectedConditions.elementToBeClickable(question));
        question.click();
        return this;
    }

    public boolean isFaqAnswerVisible(int index){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElements(faqElementsAnswer).get(index)));
        return driver.findElements(faqElementsAnswer).get(index).isDisplayed();
    }

    public boolean isErrorShownForIncorrectOrderNumber(String orderNumber){
        driver.findElement(orderStatusButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(goButton));
        driver.findElement(orderNumberField).sendKeys(orderNumber);
        driver.findElement(goButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlContains(CHECK_ORDER_URL));
        return driver.findElement(errorPicture).isDisplayed();
    }

}
