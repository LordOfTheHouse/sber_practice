package ru.sber.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sber.annotations.NotEmpty;
import ru.sber.aspect.NotEmptyAspect;
import ru.sber.models.Person;

import java.util.List;
import java.util.logging.Logger;

/**
 * Сервис по определению входит ли юзер в список друзей пользователя
 */
@Service
public class ListFriendsService implements ListFriendsServiceInterface {

    private Logger logger = Logger.getLogger(NotEmptyAspect.class.getName());
    List<Person> users = List.of(new Person(1, "Oleg", "111"),
            new Person(2, "Maxim", "112"),
            new Person(3, "Vlad", "113"));

    @Override
    @NotEmpty
    public boolean isFriend(String name, String phone) {
        logger.info("Осуществляется проверка в методе isFriend(String name, String phone)");
        return users.stream().anyMatch(user -> user.name().equals(name) && user.phoneNumber().equals(phone));
    }

    @Override
    @NotEmpty
    public boolean isFriend(Long uid) {
        logger.info("Осуществляется проверка в методе isFriend(Long uid)");
        return users.stream().anyMatch(user -> user.uid()==uid);
    }

    @Override
    @NotEmpty
    public boolean isFriend(List<Person> users) {
        logger.info("Осуществляется проверка в методе isFriends(List<Person> users)");
        return false;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
