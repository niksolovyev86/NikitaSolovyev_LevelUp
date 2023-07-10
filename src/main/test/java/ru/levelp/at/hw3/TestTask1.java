package ru.levelp.at.hw3;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.levelp.at.hw3.utils.SleepUtils;
import java.io.IOException;

public class TestTask1 extends BaseTest {

    @Test
    public void openMailRuSide() {
        String factURL = driver.getCurrentUrl();
        // открыли Mail.ru
        driver.get(getURL_MAIL());

        String factTitle = driver.getTitle();
        String expectedTitle = "Mail.ru: почта, поиск в интернете, новости, игры";

        //Проверили, что на главной странице Mail.ru
        softAssertions.assertThat(factTitle).
                      as("Wrong browser title").
                      isEqualTo(expectedTitle);

        //Нажатие кнопки Вход
        var mailEnterButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='enter-mail-primary']")));
        mailEnterButton.click();

        //Переключение на iframe регистрации/входа
        var frameTemp = wait.
            until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//iframe[contains(@class, 'ag-popup__frame__layout')]")));

        var frameLogin = driver.switchTo().frame(frameTemp);

        //Ввод логина и нажатие кнопеки Далее
        var loginInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        loginInput.sendKeys(getLOGIN());

        var loginClickButton = frameLogin.findElement(By.xpath("//button[@data-test-id='next-button']"));
        loginClickButton.click();

        //Ввод пароля и нажатие кнопки войти
        var passwordInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        passwordInput.sendKeys(getPASSWORD());

        var passwordClickButton = frameLogin.findElement(By.xpath("//button[@data-test-id='submit-button']"));
        passwordClickButton.click();

        //Переключение на основное окно вкладки
        frameLogin.switchTo().defaultContent();

        //LeftBar в mail.ru/inbox
        var leftBar = wait.
            until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//div[@class='sidebar__full sidebar__full_fixed fn-enter']")));

        //нажатие на кнопку Новое письмо
        leftBar.findElement(By.xpath("//span[@class='compose-button__wrapper']")).click();

        //Проверка перехода на mail.ru/inbox
        softAssertions.assertThat(driver.getCurrentUrl()).
                      as("Wrong page is opened!").
                      contains(getURL_MAIL_MAILBOX());

        //Переменная frameSendMail для выделения письма
        var frameSendMail = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//div[@class='compose-app__compose']")));

        //Заполнение полей address, Title и text письма
        frameSendMail.
            findElement(By.
                xpath("//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]"))
            .sendKeys(getMAIL_ADDRESS());

        frameSendMail.
            findElement(By.
                xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]"))
            .sendKeys("Test");

        frameSendMail.
            findElement(By.xpath("//div[contains(@class, 'editable-container')]//div"))
            .sendKeys("Test");

        //Нажати на кнопку Сохранить письмо
        frameSendMail.findElement(By.xpath("//button[@data-test-id='save']")).click();

        //Закрытие окна с письмом
        var closeFrameSendMailButton = frameSendMail.
            findElement(By.xpath("//div[@class='row--3ldj- margin--1eqJf']//button[3]"));
        closeFrameSendMailButton.click();


        //Переход на вкладку Drafts
        var draftTabButton = wait.
            until(ExpectedConditions.elementToBeClickable(By.
                xpath("//a[contains(@href, 'drafts')]")));
        draftTabButton.click();

        //Проверка перехода на вкладку drafts
        driver.navigate().refresh();
        String expectedDraftsURL = "https://e.mail.ru/drafts/";
        var draftsURLCheck = wait.until(ExpectedConditions.urlContains("drafts"));

        //Проверка письма в Send
        testContent();

        //Открытие письма во вкладке Drafts
        var draftLetterOpenButton = wait.
            until(ExpectedConditions.elementToBeClickable(By.
                xpath("//div[contains(@class, 'correspondent')]//..")));
        draftLetterOpenButton.click();

        //Нажатие кнопки Отправить
        var sendLetterButton = wait.
            until(ExpectedConditions.elementToBeClickable(By.
                xpath("//button[@data-test-id='send']")));
        sendLetterButton.click();
        //div[@class='compose-app__compose']

        //Закрытие окна с уведомление об отправке
        var closeTempWindowTabButton =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Закрыть']")));
        closeTempWindowTabButton.click();

        //Переход во вкладку Send
        var sendMailTabButton = wait.
            until(ExpectedConditions.elementToBeClickable(By.
                xpath("//a[contains(@href, 'sent')]")));
        sendMailTabButton.click();

        //Проверка перехода на вкладку send
        String expectedSendURL = "https://e.mail.ru/sent/";
        var sendURLCheck = wait.until(ExpectedConditions.urlContains("sent"));

        //Проверка письма в Send
        testContent();

        //Переход во вкладку Drafts
        driver.get(getURL_MAIL_MAILBOX_DRAFT());

        //Переход во вкладку Drafts для проверки его отсудствия после отправки
        softAssertions.assertThat(By.
                          xpath("//span[contains(@class, 'subject')]//span")).
                      as("There is object in draft tab!").isNotNull();

        softAssertions.assertAll();
    }

    public void testContent() {

        System.out.println(driver.getCurrentUrl());

        var factMailAddress = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'correspondent')]//span")));
        System.out.println(factMailAddress.getText());
        softAssertions.assertThat(
                          factMailAddress.getText()).
                      as("Wrong mail address in letter!").
                      isEqualTo(getMAIL_ADDRESS());

        var factMailTitle = wait.until(
                                    ExpectedConditions.visibilityOfElementLocated(By.
                                        xpath("//div[contains(@class, 'title')]//span")))
                                .getText();
        System.out.println(factMailTitle);
        softAssertions.assertThat(factMailTitle).
                      as("Wrong title in letter!").
                      contains("Test");

        var factMailText = wait.until(
                                   ExpectedConditions.visibilityOfElementLocated(By.
                                       xpath("//span[contains(@class, 'llc__snippet')]//span")));
        System.out.println(factMailText.getText());
        softAssertions.assertThat(factMailText.getText()).
                      as("Wrong text in letter!").
                      contains("Test");
    }
}
