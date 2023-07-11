package ru.levelp.at.hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestTask3 extends BaseTest {

    String letterTitle = "Не содержит ключевых слов";
    String expectedLetterTitle = "Не содержит";
    String letterText = "Тестируем сортировку по папкам";
    String expectedLetterText = "Тестируем";

    @Test
    public void openMailRuSide() {

        // открыли Mail.ru
        driver.get(getURL_MAIL());

        String factTitle = driver.getTitle();
        String expectedTitle = "Mail.ru: почта, поиск в интернете, новости, игры";

        //Проверили, что на главной странице Mail.ru
        softAssertions.assertThat(factTitle)
                      .as("Wrong browser title")
                      .isEqualTo(expectedTitle);

        //Нажатие кнопки Вход
        var mailEnterButton = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@data-testid='enter-mail-primary']")));
        mailEnterButton.click();

        //Переключение на iframe регистрации/входа
        var frameTemp = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//iframe[contains(@class, 'ag-popup__frame__layout')]")));

        var frameLogin = driver.switchTo().frame(frameTemp);

        //Ввод логина и нажатие кнопеки Далее
        var loginInput = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//input[@name='username']")));
        loginInput.sendKeys(getLogin());

        var loginClickButton = frameLogin.findElement(By.xpath("//button[@data-test-id='next-button']"));
        loginClickButton.click();

        //Ввод пароля и нажатие кнопки войти
        var passwordInput = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//input[@name='password']")));
        passwordInput.sendKeys(getPassword());

        var passwordClickButton = frameLogin.findElement(By
            .xpath("//button[@data-test-id='submit-button']"));
        passwordClickButton.click();

        //Переключение на основное окно вкладки
        frameLogin.switchTo().defaultContent();

        //LeftBar в mail.ru/inbox
        var leftBar = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='sidebar__full sidebar__full_fixed fn-enter']")));

        //нажатие на кнопку Новое письмо
        leftBar.findElement(By.xpath("//span[@class='compose-button__wrapper']")).click();

        //Проверка перехода на mail.ru/inbox
        softAssertions.assertThat(driver.getCurrentUrl())
                      .as("Wrong page is opened!")
                      .contains(getUrlMailMailbox());

        //Переменная frameSendMail для выделения письма
        var frameSendMail = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='compose-app__compose']")));

        //Заполнение полей address, Title и text письма
        frameSendMail
            .findElement(By
                .xpath("//div[contains(@class, 'head_container')]//input[contains(@class, 'container')]"))
            .sendKeys(getMailAddress());

        frameSendMail
            .findElement(By
                .xpath("//div[contains(@class, 'subject__container')]//input[contains(@class, 'container')]"))
            .sendKeys(letterTitle);

        frameSendMail
            .findElement(By.xpath("//div[contains(@class, 'editable-container')]//div"))
            .sendKeys(letterText);

        //Нажатие кнопки Отправить
        var sendLetterButton = wait
            .until(ExpectedConditions.elementToBeClickable(By
                .xpath("//button[@data-test-id='send']")));
        sendLetterButton.click();

        //Закрытие окна с уведомление об отправке
        var closeTempWindowTabButton = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[@title='Закрыть']")));
        closeTempWindowTabButton.click();

        //Переход во вкладку Inbox(toMySelf)
        var sendMailTabButton = wait
            .until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[contains(@href, 'tomyself')]")));
        sendMailTabButton.click();

        //Проверка перехода на вкладку Inbox(toMySelf)
        wait.until(ExpectedConditions.urlContains("tomyself"));

        //Проверка письма в Inbox(toMySelf)
        testContent();

        //Открытие письма во вкладке Inbox(toMySelf)
        var inboxLetterOpenButton = wait
            .until(ExpectedConditions.elementToBeClickable(By
                .xpath("//div[contains(@class, 'correspondent')]//..")));
        inboxLetterOpenButton.click();

        //Удаляем последнее письмо в папке Inbox(toMySelf)
        var deleteLetterButton = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[span[@title='Удалить']]")));
        deleteLetterButton.click();

        //Переход во вкладку Корзина
        var trashMailTabButton = wait
            .until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[contains(@href, 'trash')]")));
        trashMailTabButton.click();

        //Проверка перехода на вкладку Корзина
        wait.until(ExpectedConditions.urlContains("trash"));

        //Проверка письма в Корзина
        testContent();

        //Logout
        var userMenuButton = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@aria-label='niksolovyev86@mail.ru']")));
        userMenuButton.click();

        var userLogoutButton = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[div[contains(text(), 'Выйти')]]")));
        userLogoutButton.click();

        softAssertions.assertAll();
    }

    public void testContent() {

        var factMailAddress = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'correspondent')]//span")));
        softAssertions.assertThat(factMailAddress.getAccessibleName())
                      .as("Wrong mail address in letter!")
                      .contains(getMailAddress());

        var factMailTitle = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[contains(@class, 'title')]//span")))
            .getText();
        softAssertions.assertThat(factMailTitle)
                      .as("Wrong title in letter!")
                      .contains(expectedLetterTitle);

        var factMailText = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[contains(@class, 'llc__snippet')]//span")));
        softAssertions.assertThat(factMailText.getText())
                      .as("Wrong text in letter!")
                      .contains(expectedLetterText);
    }
}
