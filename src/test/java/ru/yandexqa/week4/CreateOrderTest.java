package ru.yandexqa.week4;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandexqa.week4.pages.ScooterMainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest {

    private final String orderButtonPosition;
    private final String[] personalData;
    private final int metroStationIndex;
    private final String[] rentData;
    private final int rentDurationIndex;

    public CreateOrderTest(String orderButtonPosition, String[] personalData, int metroStationIndex,
                           String[] rentData, int rentDurationIndex) {
        this.orderButtonPosition = orderButtonPosition;
        this.personalData = personalData;
        this.metroStationIndex = metroStationIndex;
        this.rentData = rentData;
        this.rentDurationIndex = rentDurationIndex;
    }



    @Parameterized.Parameters
    public static Object[][] getFaqParameters(){
        return new Object[][] {
                {"top", new String[] {"Иван", "Петров", "Суворовская пл., д.3", "+76665122525"}, 83,
                        new String[] {"18.07.2023", "grey", "встетить у метро"}, 3},
                {"bottom", new String[] {"Иван", "Петров", "Суворовская пл., д.3", "+76665122525"}, 83,
                        new String[] {"18.07.2023", "grey", "встетить у метро"}, 3},
                {"top", new String[] {"Анна", "Бочкова", "Красноярская ул. д3 к2 кв21", "+76245122525"}, 88,
                        new String[] {"31.03.2022", "black", ""}, 0},
                {"bottom", new String[] {"Анна", "Бочкова", "Красноярская ул. д3 к2 кв21", "+76245122525"}, 88,
                        new String[] {"31.03.2022", "black", ""}, 0},

        };
    }


    @Test
    public void isOrderSuccessful(){
        ScooterMainPage mainPage = new ScooterMainPage(driver);
        boolean isOrderNumberVisible =  mainPage.cookiesConfirmButtonClick()
                                                .orderButtonClick(orderButtonPosition)
                                                .fillDataFirstPage(personalData, metroStationIndex)
                                                .nextButtonClick()
                                                .fillDataSecondPage(rentData, rentDurationIndex)
                                                .orderButtonClick()
                                                .confirmButtonClick()
                                                .isOrderNumberVisible();
        assertTrue(isOrderNumberVisible);
    }

}
