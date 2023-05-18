package ru.yandexqa.week4.config;

public class Config {
    public static final String CHROME_PROPERTY = "chrome";
    public static final String FIREFOX_PROPERTY = "firefox";
    public static final String ROOT_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final String DZEN_URL = "https://dzen.ru/";
    public static final String CHECK_ORDER_URL = "track";
    public static final Object[][] SITE_LINKS = {
                                                    {"order"},
                                                    {CHECK_ORDER_URL},
                                                    {""},
                                                };
}
