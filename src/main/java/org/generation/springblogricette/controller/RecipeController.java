package org.generation.springblogricette.controller;

import jakarta.validation.Valid;
import org.generation.springblogricette.model.Recipe;
import org.generation.springblogricette.repository.CategoryRepository;
import org.generation.springblogricette.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model) {
        List<Recipe> recipeList = recipeRepository.findAll();
        model.addAttribute("recipeList", recipeList);
        return "/recipe/list";
    }

    @GetMapping("/show/{id}")///recipe/show/1
    public String show(@PathVariable Integer id, Model model) {
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            Recipe recipe = result.get();
            model.addAttribute("recipe", recipe);
            return "recipe/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe " + id + " not found");
        }

    }

    @GetMapping("/create")
    public String create(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        model.addAttribute("categoryList", categoryRepository.findAll());
        return "recipe/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("recipe") Recipe formRecipe, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("recipeList", recipeRepository.findAll());
            model.addAttribute("categoryList", categoryRepository.findAll());
            return "recipe/create";
        } else {

            Recipe savedRecipe = recipeRepository.save(formRecipe);
            return "redirect:/recipes/show/" + savedRecipe.getId();
        }
    }

    @GetMapping("/edit/{id}")///recipes/edit/1
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("recipe", result.get());
            model.addAttribute("categoryList", categoryRepository.findAll());
            return "/recipe/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("recipe") Recipe formRecipe, BindingResult bindingResult, Model mdoel) {
        Optional<Recipe> result = recipeRepository.findById(id);
        if (result.isPresent()) {
            Recipe recipeToEdit = result.get();
            Recipe savedRecipe = recipeRepository.save(formRecipe);
            return "redirect:/recipes/show/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Recipe> result = recipeRepository.findById(id);
        redirectAttributes.addFlashAttribute("redirectMessage", result.get().getTitle() + " recipe, deleted!");
        if (result.isPresent()) {
            recipeRepository.deleteById(id);
            return "redirect:/recipes";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id " + id + " not found");
        }
    }
}
