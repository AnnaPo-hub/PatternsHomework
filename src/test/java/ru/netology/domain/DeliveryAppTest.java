package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryAppTest {
    private RegistrationByCardInfo registrationInfo;

    @BeforeEach
    void setUpAll() {
        registrationInfo = DataGenerator.Registration.generateByCard("ru");
    }

    @Test
    void shouldSubmitRequestWithDateAsString() {
        open("http://localhost:9999");
        $("input[placeholder=\"Город\"").setValue(registrationInfo.getCity());
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("input[placeholder=\"Дата встречи\"]").setValue(DataGenerator.Registration.generateDatePlus3());
        $("input[name=\"name\"]").setValue(registrationInfo.getName());
        $("input[name=\"phone\"]").setValue(registrationInfo.getPhone().toString());
        $("[data-test-id=agreement]").click();
        $$("span.button__text").find(exactText("Запланировать")).click();
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("input[placeholder=\"Дата встречи\"]").sendKeys(DataGenerator.Registration.generateDatePlus10());
        $$("span.button__text").find(exactText("Запланировать")).click();
        $$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
    }
}
