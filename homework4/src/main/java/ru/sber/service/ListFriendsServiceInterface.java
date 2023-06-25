package ru.sber.service;

import ru.sber.models.Person;

import java.util.List;
import java.util.logging.Logger;

/**
 * Сервис по проверке списка друзей
 */
public interface ListFriendsServiceInterface {
    /**
     * Проверяет есть ли у пользователя друг с данным uid
     *
     * @param uid - ид друга
     * @return возвращает true если друг есть
     */
    boolean isFriend(Long uid);

    /**
     * Проверяет есть ли у пользователя друг с данным именем
     *
     * @param name  - имя друга
     * @param phone - номер телефона друга
     * @return возвращает true если друг есть
     */
    boolean isFriend(String name, String phone);

    /**
     * Проверяет есть ли у пользователя друзья с данным именем
     *
     * @param users - друзья
     * @return возвращает true если все users являются друзья
     */
    boolean isFriend(List<Person> users);

    void setLogger(Logger logger);
}
