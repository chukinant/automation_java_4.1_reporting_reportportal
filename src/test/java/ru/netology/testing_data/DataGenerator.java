package ru.netology.testing_data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import lombok.Value;

public class DataGenerator {
    private DataGenerator() {
        Faker faker = new Faker();
    }

    public static String generateCity(String locale) {
        String[] cities = new String[] {"Барнаул", "Белгород", "Биробиджан", "Великий Новгород", "Владивосток",
                "Владимир", "Вологда", "Волгоград", "Воронеж",
                "Грозный", "Иркутск", "Калининград", "Калуга", "Кемерово", "Кострома", "Краснодар",
                "Красноярск", "Курск", "Липецк", "Москва", "Мурманск", "Нижний Новгород",
                "Новосибирск", "Омск", "Оренбург", "Пермь", "Псков", "Ростов-на-Дону",
                "Рязань", "Самара", "Саратов", "Смоленск", "Сочи", "Ставрополь", "Таганрог", "Тамбов", "Тверь",
                "Томск", "Тула", "Тюмень", "Уфа", "Хабаровск", "Челябинск", "Чита", "Элиста", "Ярославль"
        };
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class UserRegistration {
        private UserRegistration() {
        }
        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity("ru"), generateName("ru"), generatePhone("ru"));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}