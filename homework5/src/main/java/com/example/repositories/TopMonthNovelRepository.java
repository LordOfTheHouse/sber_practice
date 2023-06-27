package com.example.repositories;

import com.example.models.Novel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранит топ новелл за месяц
 */
@Repository
public class TopMonthNovelRepository implements NovelsRepositoryInterface{
    private List<Novel> topMonth = new ArrayList<>(List.of(new Novel(
                    "Lord of the Magical Beasts"
                    , ""
                    , "/img/31479.jpg"
                    , List.of("Love", "Daughter")),
            new Novel("Bokutachi wa Benkyou ga Dekinai",
                    "",
                    "img/bokutachi.jpg"
                    , List.of("Love", "Game", "Romance")),
            new Novel(
                    "Inoru Kami Wo Sirazu, Negau Kokoro no Katachi wo Miezu",
                    "",
                    "img/inoru.png"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Ankoku Kishi to Issho!",
                    "",
                    "img/ankoku.png"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Asahina Wakaba to Marumaru na Kareshi",
                    "",
                    "img/asahina.png"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Boku no Kanojo Sensei",
                    "",
                    "img/bokuno.jpg"
                    , List.of("Love", "Game", "Romance"))
    ));
    @Override
    public List<Novel> getNovels() {
        return topMonth;
    }
}
