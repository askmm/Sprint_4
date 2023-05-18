package ru.yandexqa.week4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ScooterOrderCalendar {
    private WebDriver driver;

    private By prevMonthButton = By.xpath(".//div[@class='react-datepicker']//button[@aria-label = 'Previous Month']");
    private By nextMonthButton = By.xpath(".//div[@class='react-datepicker']//button[@aria-label = 'Next Month']");
    private By currentMonthField = By.className("react-datepicker__current-month");
    private final String dateXpathPattern = ".//div[@class = 'react-datepicker__week']//div[contains(@aria-label, '$date$')]";

    public ScooterOrderCalendar(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseDate(String dateString){
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern( "d.MM.yyyy" );
        LocalDate date = LocalDate.parse(dateString, inputFormat);
        YearMonth currentMonth = YearMonth.parse(driver.findElement(currentMonthField).getText(),
                                                    DateTimeFormatter.ofPattern("LLLL yyyy", new Locale("ru")));
        YearMonth targetMonth = YearMonth.from(date);
        int numberOfClicks = (int)ChronoUnit.MONTHS.between(currentMonth, targetMonth);
        By monthButton = numberOfClicks > 0 ? nextMonthButton : prevMonthButton;
        numberOfClicks = Math.abs(numberOfClicks);
        for (int i = 0; i < numberOfClicks; i++) {
            driver.findElement(monthButton).click();
        }
        String searchDateString = date.format(DateTimeFormatter.ofPattern("d-е MMMM yyyy г.", new Locale("ru")));
        driver.findElement(By.xpath(dateXpathPattern.replace("$date$", searchDateString))).click();
    }
}
