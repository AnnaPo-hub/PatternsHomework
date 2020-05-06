package ru.netology.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryAppTest {
    private Faker faker;

    @BeforeEach
    void setUoAll(){
        faker = new Faker(new Locale("ru"));

    }

    @Test
    void shouldSubmitRequestWithDateAsString() {
        open("http://localhost:9999");
        String city = faker.address().city();
        $("input[placeholder=\"Город\"").setValue(city);
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, 3);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        String dateForTest = format1.format(c.getTime());
        for (int i=0; i<10; i++){
            $("input[placeholder=\"Дата встречи\"]"). sendKeys(Keys.BACK_SPACE);}
        $("input[placeholder=\"Дата встречи\"]"). sendKeys(dateForTest);
        String name = faker.name().fullName();
        $("input[name=\"name\"]").setValue(name);
        String number = faker.phoneNumber().toString();
        $("input[name=\"phone\"]").setValue(number);
        $("[data-test-id=agreement]").click();
        $$("span.button__text").find(exactText("Запланировать")).click();
//        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        Calendar с2 = new GregorianCalendar();
        с2.add(Calendar.DAY_OF_YEAR, 13);
        SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy");
        String dateForTest2 = format2.format(с2.getTime());
        for (int i=0; i<10; i++){
            $("input[placeholder=\"Дата встречи\"]"). sendKeys(Keys.BACK_SPACE);}
        $("input[placeholder=\"Дата встречи\"]"). sendKeys(dateForTest2);
        $$("span.button__text").find(exactText("Запланировать")).click();
        $$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);




    }
}
