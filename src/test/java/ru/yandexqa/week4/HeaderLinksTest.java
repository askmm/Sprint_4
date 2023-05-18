package ru.yandexqa.week4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandexqa.week4.pages.ScooterPageHeader;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static ru.yandexqa.week4.config.Config.*;

@RunWith(Parameterized.class)
public class HeaderLinksTest extends BaseTest {
    String startPage;
    ScooterPageHeader pageHeader;

    public HeaderLinksTest(String startPage) {
        this.startPage = startPage;
    }

    @Before
    public void setPage(){
        driver.get(ROOT_URL + startPage);
        pageHeader = new ScooterPageHeader(driver);
    }

    @Parameterized.Parameters
    public static Object[][] getParams(){
        return SITE_LINKS;
    }

    @Test
    public void isTransferredToHomePage(){
        pageHeader.scooterLogoClick();
        assertEquals(ROOT_URL, driver.getCurrentUrl());
    }

    @Test
    public void isTransferredToDzenPage(){
        pageHeader.yandexLogoClick();
        String currWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        String newUrl = "";
        for (String handle: windowHandles) {
            if (!handle.equals(currWindow)) {
                driver.switchTo().window(handle);
                new WebDriverWait(driver, 5)
                        .until(ExpectedConditions.urlContains(DZEN_URL));
                newUrl = driver.getCurrentUrl();
                break;
            }
        }
        assertEquals(DZEN_URL, newUrl.split("\\?")[0]);
    }
}
