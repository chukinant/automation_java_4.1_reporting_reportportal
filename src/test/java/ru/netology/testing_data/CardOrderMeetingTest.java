package ru.netology.testing_data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.testing_data.DataGenerator.UserRegistration.generateUser;

public class CardOrderMeetingTest {

    @Test
    void shouldReplanMeetingTest() {
        var validUser = generateUser("ru");
        var daysToAddFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddFirstMeeting);
        var daysToAddSecondMeeting = 6;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddSecondMeeting);

        var cardOrderPage = Selenide.open("http://localhost:9999/", CardOrderPage.class);
        cardOrderPage.fillForm(validUser);

        //назначение даты и проверка
        SelenideElement dateField = $x("//*[@data-test-id='date']//input");
        dateField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateField.setValue(firstMeetingDate);

        $x("//*[@data-test-id='agreement']//input").click();
        $x("//button[descendant::*[contains(text(),'Запланировать')]]").click();

        $(Selectors.withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));

        $x("//*[@data-test-id='success-notification']//*[starts-with(text(),'Встреча успешно запланирована на')]").
                should(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(firstMeetingDate));

        //перепланирование на другую дату и проверки
        dateField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateField.setValue(secondMeetingDate);
        $x("//button[descendant::*[contains(text(),'Запланировать')]]").click();

        $(Selectors.withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(Selectors.withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible, Duration.ofSeconds(15));

        $x("//button[descendant::*[contains(text(),'Перепланировать')]]").click();
        $x("//*[@data-test-id='success-notification']//*[starts-with(text(),'Встреча успешно запланирована на')]").
                should(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(secondMeetingDate));
    }
}