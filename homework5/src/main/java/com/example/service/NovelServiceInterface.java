package com.example.service;

import com.example.models.Novel;

import java.util.List;

/**
 * Содержит логику поиска новелл
 */
public interface NovelServiceInterface {
    /**
     * Определяет книги, которые требуется вывести
     * @param name - название новеллы или его часть
     * @return новеллы подходящие под поиск
     */
    List<Novel> searchNovel(String name);
}
