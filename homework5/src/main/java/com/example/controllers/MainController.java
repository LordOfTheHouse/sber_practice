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

  @GetMapping("/novels")
  public String viewProducts(@RequestParam(required = false) String name, Model model) {
    model.addAttribute("novels", novelService.searchNovel(name));
    return "novels_search.html";
  }

  @GetMapping("/home")
  public String addProduct(
          @RequestParam(required = false) String name,
          Model model
  ) {
    if(name != null) return viewProducts(name, model);
    model.addAttribute("novels", novelService.searchNovel(""));
    return "home2.html";
  }


}
