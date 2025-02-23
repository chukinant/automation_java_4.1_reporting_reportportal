package ru.netology.reporting.reportportal;

import com.codeborne.selenide.SelenideElement;

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


