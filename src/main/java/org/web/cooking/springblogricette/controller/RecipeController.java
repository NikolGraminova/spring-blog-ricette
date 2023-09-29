package org.web.cooking.springblogricette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.web.cooking.springblogricette.model.Recipe;
import org.web.cooking.springblogricette.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/list")
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping()
    private String index(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        return "recipes/list";
    }

    @GetMapping("/show/{id}")
    private String show(@PathVariable("id") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
            return "recipes/details";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    private String create(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/form";
    }

    @PostMapping("/create")
    private String doCreate(@ModelAttribute("recipe") Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    private String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
            return "recipes/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{id}")
    private String doEdit(@PathVariable("id") Integer id, @ModelAttribute("recipe") Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    private String delete(@PathVariable("id") Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/list";
    }


}
