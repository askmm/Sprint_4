package ru.yandexqa.week4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScooterPageHeader {
    WebDriver driver;

    public ScooterPageHeader(WebDriver driver) {
        this.driver = driver;
    }

    private By scooterLogo = By.xpath(".//a[@class='Header_LogoScooter__3lsAR']");
    private By yandexLogo = By.xpath(".//a[@class='Header_LogoYandex__3TSOI']");

    public void scooterLogoClick(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(scooterLogo));
        driver.findElement(scooterLogo).click();
    }

    public void yandexLogoClick(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(yandexLogo));
        driver.findElement(yandexLogo).click();
    }

}
