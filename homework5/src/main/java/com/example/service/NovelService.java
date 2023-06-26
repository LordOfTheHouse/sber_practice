package com.example.service;

import com.example.models.Novel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для поиска новелл, для вывода на страницу novels
 */
@Service
public class NovelService implements NovelServiceInterface{
    private List<Novel> novels = new ArrayList<>(List.of(new Novel(
                    "Anata wo Akiramekirenai Moto Iinazuke ja Dame desu ka?"
                    , " Широки Цубаса и Нана - туда, где ваша заурядная пара друзей детства. " +
                    "Но после того, как их родители решили объявить их женихами, они оба стали лучше " +
                    "относиться друг к другу, и как раз в тот момент, когда их должен был ожидать милый " +
                    "и трогательный роман — Нанато был вынужден уехать со своей матерью!"
                    , "/img/anata.png"
                    ,List.of("Love", "Daughter")),
            new Novel("Bokutachi wa Benkyou ga Dekinai",
                    "Нариюки Юига, ученик с оценками выше среднего, был назначен репетитором для трёх гениев, " +
                            "каждый из которых был лучшим в своём предмете, чтобы получить стипендию и не платить за образование в будущем. Казалось бы, чему нужно обучать гениев? А всё не так просто, ведь, как оказалось, " +
                            "каждый гений был хорош исключительно в своём предмете, " +
                            "а про другие — вообще слыхом не слыхивал.",
                    "img/bokutachi.jpg"
                    , List.of("Love", "Game", "Romance")),
            new Novel(
                    "Yumemiru Danshi wa Genjitsushugisha",
                    "Саджо Ватару увлечен своей одноклассницей Нацукавой Айкой до такой степени," +
                            " что он живет в мечтах об их взаимной любви и отношениях, безжалостно приближаясь" +
                            " к ней при каждом удобном случае. Однако однажды Ватару очнётся, " +
                            "и ему придется столкнуться с реальностью.",
                    "img/yememiru.png"
                    , List.of("Love", "Game", "Romance"))
    ));


    @Override
    public List<Novel> searchNovel(String name){
        return novels.stream()
                .filter(novel -> novel.getTitle().matches("(?i).*"+name.strip()+".*"))
                .collect(Collectors.toList());
    }


}
