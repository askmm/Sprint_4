package ru.yandexqa.week4;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandexqa.week4.config.WebDriverFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    @Before
    public void init(){
        driver = WebDriverFactory.getWebDriver();
    }

    @After
    public void close(){
        driver.quit();
    }
}
