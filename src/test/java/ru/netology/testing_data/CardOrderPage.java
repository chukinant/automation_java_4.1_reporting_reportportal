package ru.netology.testing_data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CardOrderPage {
    private SelenideElement cityField = $x("//*[@data-test-id='city']//input");
    private SelenideElement firstLastNameField = $x("//*[@data-test-id='name']//input");
    private SelenideElement phoneNumberField = $x("//*[@data-test-id='phone']//input");

    public CardOrderPage fillForm(DataGenerator.UserInfo info) {
        cityField.setValue(info.getCity());
        firstLastNameField.setValue(info.getName());
        phoneNumberField.setValue(info.getPhone());
        return new CardOrderPage();
    }
}


