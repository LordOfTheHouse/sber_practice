package com.example.controllers;

import com.example.models.Novel;
import com.example.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Содержит логику отображения страниц home и novel
 */
@Controller
public class MainController {

  private final NovelService novelService;

  public MainController(NovelService novelService) {
    this.novelService = novelService;
  }

  /**
   * Отображает страницу novels
   * @param name - наименование новеллы
   * @return название html файла - страницы сайта
   */
  @GetMapping("/novels")
  public String viewNovels(@RequestParam(required = false) String name, Model model) {
    model.addAttribute("novels", novelService.searchNovel(name));
    return "novels_search.html";
  }

  /**
   * Отображает страницу home
   * @param name - наименование новеллы
   * @return название html файла - страницы сайта
   */
  @GetMapping("/home")
  public String viewHome(
          @RequestParam(required = false) String name,
          Model model
  ) {
    if(name != null) return viewNovels(name, model);
    model.addAttribute("top_month", novelService.getTopMonth());
    model.addAttribute("top_week", novelService.getTopWeek());
    model.addAttribute("top_day", novelService.getTopDay());
    model.addAttribute("novels", novelService.searchNovel(""));
    return "home2.html";
  }


}
