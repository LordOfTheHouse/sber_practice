package com.example.repositories;

import com.example.models.Novel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранит топ новелл за неделю
 */
@Repository
public class TopWeekNovelRepository implements NovelsRepositoryInterface {
    private List<Novel> topWeek = new ArrayList<>(List.of(new Novel(
                    "Bokutachi wa Benkyou ga Dekinai"
                    , ""
                    , "/img/bokutachi.png"
                    , List.of("Love", "Daughter")),
            new Novel("Bokutachi wa Benkyou ga Dekinai",
                    "",
                    "img/bokutachi.jpg"
                    , List.of("Love", "Game", "Romance")),
            new Novel(
                    "Inkyara na Ore to Ichatsukitai tte Maji kayo",
                    "",
                    "img/inkyara.jpg"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Inoru Kami Wo Sirazu, Negau Kokoro no Katachi wo Miezu",
                    "",
                    "img/inoru.png"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Boku no Kanojo Sensei",
                    "",
                    "img/boku.jpg"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Anata wo Akiramekirenai Moto Iinazuke ja Dame desu ka?",
                    "",
                    "img/anata.png"
                    , List.of("Love", "Game", "Romance"))
    ));
    @Override
    public List<Novel> getNovels() {
        return topWeek;
    }
}
