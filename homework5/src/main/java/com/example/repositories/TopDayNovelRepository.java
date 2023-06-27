package com.example.repositories;

import com.example.models.Novel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранит топ новелл за день
 */

@Repository
public class TopDayNovelRepository implements NovelsRepositoryInterface {
    private List<Novel> topDay = new ArrayList<>(List.of(new Novel(
                    "Ankoku Kishi to Issho!"
                    , ""
                    , "/img/ankoku.png"
                    , List.of("Love", "Daughter")),
            new Novel("Lord of the Magical Beasts",
                    "",
                    "img/31479.jpg"
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
            new Novel("Yumemiru Danshi wa Genjitsushugisha",
                    "",
                    "img/yememiru.png"
                    , List.of("Love", "Game", "Romance")),
            new Novel("Kibishii Onna Joushi ga Koukousei ni Modottara Ore ni Dere Dere suru Riyuu",
                    "",
                    "img/kibishii.png"
                    , List.of("Love", "Game", "Romance"))
    ));
    @Override
    public List<Novel> getNovels() {
        return topDay;
    }
}
