package ru.yandexqa.week4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterCookies {
    private WebDriver driver;
    private By confirmCookieButton = By.id("rcc-confirm-button");

    public ScooterCookies(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmCookieButtonClick(){
        try {
            driver.findElement(confirmCookieButton).click();
        } catch (NoSuchElementException e) {

        }
    }
}
