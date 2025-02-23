package ru.netology.reporting.reportportal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardOrderMeetingTest {
    private final DataGenerator.UserInfo validUser = DataGenerator.UserRegistration.generateUser("ru");
    private final int daysToAddFirstMeeting = 4;
    private final String firstMeetingDate = DataGenerator.generateDate(daysToAddFirstMeeting);
    private final SelenideElement dateField = $x("//*[@data-test-id='date']//input");
    private final SelenideElement nameField = $x("//*[@data-test-id='name']//input");

    @BeforeEach
    void setUp() {
        var cardOrderPage = Selenide.open("http://localhost:9999/", CardOrderPage.class);
        cardOrderPage.fillForm(validUser);
    }

    @Test
    void shouldReplanMeetingTest() {
        var daysToAddSecondMeeting = 6;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddSecondMeeting);
        //назначение даты и проверка
        dateField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateField.setValue(firstMeetingDate);
        $x("//*[contains(@class,'calendar__day_state_current')]").click();
        $x("//label[@data-test-id='agreement']").click();
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

    @Test
    @DisplayName("User cannot replan meeting without specifying last name")
    void shouldNotReplanMeetingTest() {
        //корректировка значения в поле "Фамилия и имя"
        nameField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        nameField.setValue("Оксана");
        //назначение даты и проверка
        dateField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        dateField.setValue(firstMeetingDate);
        $x("//*[contains(@class,'calendar__day_state_current')]").click();
        $x("//label[@data-test-id='agreement']").click();
        $x("//button[descendant::*[contains(text(),'Запланировать')]]").click();

        $(Selectors.withText("Успешно")).shouldNot(Condition.visible, Duration.ofSeconds(1));

        $x("//*[@data-test-id='name']//*[@class='input__sub']").
                should(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}