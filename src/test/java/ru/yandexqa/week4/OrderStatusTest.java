package ru.yandexqa.week4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandexqa.week4.pages.ScooterMainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderStatusTest extends BaseTest{
    private final String orderNumber;

    public OrderStatusTest(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getParams(){
        return new Object[][] {
                {"123"},
                {Integer.toString(Integer.MAX_VALUE)},
        };
    }

    @Test
    public void isErrorShownForIncorrectOrderNumber(){
        ScooterMainPage mainPage = new ScooterMainPage(driver);
        assertTrue(mainPage.isErrorShownForIncorrectOrderNumber(orderNumber));
    }
}
