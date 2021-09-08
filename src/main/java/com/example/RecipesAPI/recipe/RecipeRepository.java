package com.example.RecipesAPI.recipe;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

    Optional<List<Recipe>> findByName(String name);

    Optional<List<Recipe>> findAllByIngredientsContains(String ingredients);
}
