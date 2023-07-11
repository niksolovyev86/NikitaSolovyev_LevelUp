package ru.levelp.at.hw3;

import java.time.Duration;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    WebDriver driver;
    public WebDriverWait wait;
    SoftAssertions softAssertions;
    private final String urlMail = "https://mail.ru/";
    private final String urlMailMailbox = "https://e.mail.ru/inbox/";
    private final String urlMailMailboxDraft = "https://e.mail.ru/drafts/";
    private final String login = "niksolovyev86";
    private final String password = "Cfntkbn_5556";
    private final String mailAddress = login + "@mail.ru";

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getURL_MAIL() {
        return urlMail;
    }

    public String getUrlMailMailboxDraft() {
        return urlMailMailboxDraft;
    }

    public String getUrlMailMailbox() {
        return urlMailMailbox;
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
