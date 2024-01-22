package org.generation.springblogricette.controller;

import org.generation.springblogricette.model.Recipe;
import org.generation.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired RecipeRepository recipeRepository;
    @GetMapping
    public String index(Model model){
        List<Recipe> recipeList= recipeRepository.findAll();
        model.addAttribute("recipeList",recipeList);
        return "/recipes/index";
    }
}
