package ru.sber.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.sber.exceptions.ArgumentIsNullException;
import ru.sber.exceptions.CollectionArgumentIsEmptyException;
import ru.sber.exceptions.StringArgumentIsEmptyException;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Аспект для проверки аргументов на null, на отсутствие элементов в коллекции или пустоту строк
 */
@Aspect
@Component
public class NotEmptyAspect {
    private Logger logger = Logger.getLogger(NotEmptyAspect.class.getName());

    @Before("@annotation(ru.sber.annotations.NotEmpty)")
    public void checkArgumentsMethod(JoinPoint joinPoint)
            throws Throwable {
        logger.info("Logging Aspect: Calling the intercepted method");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                throw new ArgumentIsNullException();
            } else if (arg instanceof String string && string.isEmpty()) {
                throw new StringArgumentIsEmptyException();
            } else if (arg instanceof Collection collection && collection.isEmpty()) {
                throw new CollectionArgumentIsEmptyException();
            }
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
