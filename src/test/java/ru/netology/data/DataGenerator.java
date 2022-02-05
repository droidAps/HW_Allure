package ru.netology.data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        @Step("Генерация пользователя (Россия)")
        public static RegistrationInfo generateRusUser(String city) {
            Faker faker = new Faker(new Locale("ru"));
            String fullName = faker.name().fullName();
            String[] words = fullName.split(" ");
            String userName = words[0] + " " + words[1];
            return new RegistrationInfo(
                    city,
                    userName,
                    faker.phoneNumber().phoneNumber());
        }

        @Step("Генерация даты встречи")
        public static String dateMeeting(int daysAfter) {
            return LocalDate.now().plusDays(daysAfter).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }
}
