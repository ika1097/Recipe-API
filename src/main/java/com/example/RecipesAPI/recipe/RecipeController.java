package com.example.RecipesAPI.recipe;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe/")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Recipe>> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @GetMapping("/public/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<Recipe> getRecipeById(@PathVariable String id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/public/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Recipe>> getRecipeByName(@PathVariable String name) {
        return recipeService.getRecipeByName(name);
    }

    @GetMapping("/public/findByIngredients/{ingredients}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<Recipe>> getRecipeByIngredients(@PathVariable String ingredients) {
        return recipeService.getRecipeByIngredients(ingredients);
    }

    @PostMapping("/user/addRecipe")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/user/updateRecipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateRecipe(@PathVariable String id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/admin/deleteRecipe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteRecipe(@PathVariable String id) {
        return recipeService.deleteRecipe(id);
    }
}
