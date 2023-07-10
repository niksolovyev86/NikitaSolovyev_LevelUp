package ru.levelp.at.hw3;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    public WebDriverWait wait;
    SoftAssertions softAssertions;
    private final String URL_MAIL = "https://mail.ru/";
    private final String URL_MAIL_MAILBOX = "https://e.mail.ru/inbox/";
    private final String URL_MAIL_MAILBOX_DRAFT = "https://e.mail.ru/drafts/";

    private final String URL_MAIL_MAILBOX_SEND = "https://e.mail.ru/sent/";

    private final String LOGIN = "";
    private final String PASSWORD = "";
    private final String MAIL_ADDRESS = LOGIN + "@mail.ru";
    public String getLOGIN() {
        return LOGIN;
    }

    public String getURL_MAIL_MAILBOX_SEND() {
        return URL_MAIL_MAILBOX_SEND;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getMAIL_ADDRESS() {
        return MAIL_ADDRESS;
    }

    public String getURL_MAIL() {
        return URL_MAIL;
    }

    public String getURL_MAIL_MAILBOX_DRAFT() {
        return URL_MAIL_MAILBOX_DRAFT;
    }

    public String getURL_MAIL_MAILBOX() {
        return URL_MAIL_MAILBOX;
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        softAssertions = new SoftAssertions();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        softAssertions = null;
    }
}
