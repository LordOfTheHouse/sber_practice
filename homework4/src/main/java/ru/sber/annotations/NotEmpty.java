package ru.sber.annotations;

import java.lang.annotation.*;

/**
 * Требуется для работы с классом NotEmptyAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
}