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
     * @param uid - ид дргуа
     * @return возвращает true если друг есть
     */
    public boolean isFriend(Long uid);

    /**
     * Проверяет есть ли у пользователя друг с данным именем
     *
     * @param name  - имя дргуа
     * @param phone - номер телефона дргуа
     * @return возвращает true если друг есть
     */
    public boolean isFriend(String name, String phone);

    /**
     * Проверяет есть ли у пользователя друзья с данным именем
     *
     * @param users - друзья
     * @return возвращает true если все users являются друзья
     */
    public boolean isFriend(List<Person> users);

    public void setLogger(Logger logger);
}
