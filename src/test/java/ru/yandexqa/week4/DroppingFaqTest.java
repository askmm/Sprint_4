package ru.yandexqa.week4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandexqa.week4.pages.ScooterCookies;
import ru.yandexqa.week4.pages.ScooterMainPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DroppingFaqTest extends BaseTest {
    private final int indexOfQuestion;
    private final int indexOfAnswer;

    public DroppingFaqTest(int indexOfQuestion, int indexOfAnswer) {
        this.indexOfQuestion = indexOfQuestion;
        this.indexOfAnswer = indexOfAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getFaqParameters(){
        return new Object[][] {
               {0, 0},
               {1, 1},
               {2, 2},
               {3, 3},
               {4, 4},
               {5, 5},
               {6, 6},
               {7, 7},
        };
    }

    @Test
    public void isFaqAnswerVisibleAfterClick(){
        ScooterMainPage objMainPage = new ScooterMainPage(driver);
        boolean isAnswerVisible = objMainPage.faqQuestionClick(indexOfQuestion)
                                             .isFaqAnswerVisible(indexOfAnswer);
        assertTrue(isAnswerVisible);
    }




}
