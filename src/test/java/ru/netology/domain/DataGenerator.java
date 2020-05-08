package ru.netology.domain;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    faker.address().city(),
                    faker.name().fullName(),
                    faker.phoneNumber()
            );
        }

        public static String generateDatePlus3() {
            LocalDate now = LocalDate.now().plusDays(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            return now.format(formatter);
        }

        public static String generateDatePlus10() {
            LocalDate now = LocalDate.now().plusDays(10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            return now.format(formatter);
        }
    }
}
