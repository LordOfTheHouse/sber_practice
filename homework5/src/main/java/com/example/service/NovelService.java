package com.example.service;

import com.example.models.Novel;
import com.example.repositories.NovelRepository;
import com.example.repositories.TopDayNovelRepository;
import com.example.repositories.TopMonthNovelRepository;
import com.example.repositories.TopWeekNovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для поиска новелл, для вывода на страницу novels
 */
@Service
public class NovelService implements NovelServiceInterface {
    private final NovelRepository novelRepository;
    private final TopMonthNovelRepository topMonthNovelRepository;
    private final TopWeekNovelRepository topWeekNovelRepository;
    private final TopDayNovelRepository topDayNovelRepository;

    public NovelService(NovelRepository novelRepository, TopMonthNovelRepository topMonthNovelRepository,
                        TopWeekNovelRepository topWeekNovelRepository, TopDayNovelRepository topDayNovelRepository) {
        this.novelRepository = novelRepository;
        this.topMonthNovelRepository = topMonthNovelRepository;
        this.topWeekNovelRepository = topWeekNovelRepository;
        this.topDayNovelRepository = topDayNovelRepository;
    }

    @Override
    public List<Novel> searchNovel(String name) {
        if (name == null) return novelRepository.getNovels();
        return novelRepository.getNovels().stream()
                .filter(novel -> novel.getTitle().matches("(?i).*" + name.strip() + ".*"))
                .collect(Collectors.toList());

    }

    /**
     * @return список лучших новелл за месяц
     */
    public List<Novel> getTopMonth() {
        return topMonthNovelRepository.getNovels();
    }

    /**
     * @return список лучших новелл за неделю
     */
    public List<Novel> getTopWeek() {
        return topWeekNovelRepository.getNovels();
    }

    /**
     * @return список лучших новелл за день
     */
    public List<Novel> getTopDay() {
        return topDayNovelRepository.getNovels();
    }
}
