package com.example.repositories;


import com.example.models.Novel;

import java.util.List;

/**
 * Используется для хранения новелл на сайте
 */
public interface NovelsRepositoryInterface {
    /**
     * Возвращает список новелл
     */
    List<Novel> getNovels();
}
