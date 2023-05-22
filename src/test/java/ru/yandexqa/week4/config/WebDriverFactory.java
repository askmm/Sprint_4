package ru.yandexqa.week4.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;

import static ru.yandexqa.week4.config.Config.CHROME_PROPERTY;
import static ru.yandexqa.week4.config.Config.FIREFOX_PROPERTY;

public class WebDriverFactory {
    public static WebDriver getWebDriver(){
        String browser = CHROME_PROPERTY; //System.getProperty("WebDriverName");
        WebDriver driver;
        switch (browser) {
            case (CHROME_PROPERTY):
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case (FIREFOX_PROPERTY):
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                var service = new GeckoDriverService.Builder().usingPort(4444)
                                        .usingDriverExecutable(new File("/snap/bin/geckodriver"))
                                        .build();
                var options = new FirefoxOptions();
                driver = new FirefoxDriver(service, options);
                break;
            default:
                throw new WebDriverException();
        }
        return driver;
    }
}
