package ru.sber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.sber.aspect.NotEmptyAspect;
import ru.sber.exceptions.ArgumentIsNullException;
import ru.sber.exceptions.CollectionArgumentIsEmptyException;
import ru.sber.exceptions.StringArgumentIsEmptyException;
import ru.sber.models.Person;
import ru.sber.service.ListFriendsServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ru.sber.config.ProjectConfig.class})
public class AppTest {
    private Logger serviceLogger;
    private Logger aspectLogger;
    @Autowired
    private ListFriendsServiceInterface listFriendsService;
    @Autowired
    private NotEmptyAspect notEmptyAspect;

    @BeforeEach
    public void before() {
        this.serviceLogger = mock(Logger.class);
        listFriendsService.setLogger(serviceLogger);
        this.aspectLogger = mock(Logger.class);
        notEmptyAspect.setLogger(aspectLogger);
    }

    @Test
    @DisplayName("Проверка корректного имени и номера телефона")
    public void testIsFriendByNameAndPhone() {
        boolean result = listFriendsService.isFriend("Oleg", "111");
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка корректного uid")
    public void testIsFriendByUid() {
        boolean result = listFriendsService.isFriend(1L);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Тест null имени")
    public void testIsFriendWithNullName() {
        Assertions.assertThrows(ArgumentIsNullException.class, () -> {
            listFriendsService.isFriend(null, "111");
        });
    }

    @Test
    @DisplayName("Тест пустого имени")
    public void testIsFriendWithEmptyName() {
        Assertions.assertThrows(StringArgumentIsEmptyException.class, () -> {
            listFriendsService.isFriend("", "111");
        });
    }

    @Test
    @DisplayName("Тест null uid")
    public void testIsFriendWithNullUid() {
        Assertions.assertThrows(ArgumentIsNullException.class, () -> {
            listFriendsService.isFriend((Long) null);
        });
    }

    @Test
    @DisplayName("Тест пустого списка друзей")
    public void testIsFriendWithEmptyUsers() {
        Assertions.assertThrows(CollectionArgumentIsEmptyException.class, () -> {
            listFriendsService.isFriend(new ArrayList<Person>());
        });
    }

    @Test
    @DisplayName("Test that both aspects intercept the execution" +
            " of isFriend(String name, String phone) method.")
    public void testAspectIsFriendMethodByNameAndPhone() {

        listFriendsService.isFriend("Oleg", "111");
        verify(aspectLogger).info("Logging Aspect: Calling the intercepted method");
        verify(serviceLogger).info("Осуществляется проверка в методе isFriend(String name, String phone)");
    }

    @Test
    @DisplayName("Test that both aspects intercept the execution" +
            " of isFriend(Long uid) method.")
    public void testAspectIsFriendMethodByUid() {

        listFriendsService.isFriend(1L);
        verify(aspectLogger).info("Logging Aspect: Calling the intercepted method");
        verify(serviceLogger).info("Осуществляется проверка в методе isFriend(Long uid)");
    }

    @Test
    @DisplayName("Test that both aspects intercept the execution" +
            " of isFriends(List<Person> users) method.")
    public void testAspectIsFriendMethodByUsers() {
        List<Person> users = List.of(new Person(1L, "1", "1"));
        listFriendsService.isFriend(users);
        verify(aspectLogger).info("Logging Aspect: Calling the intercepted method");
        verify(serviceLogger).info("Осуществляется проверка в методе isFriends(List<Person> users)");
    }
}
