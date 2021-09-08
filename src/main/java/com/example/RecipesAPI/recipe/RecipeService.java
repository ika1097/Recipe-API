package com.example.RecipesAPI.recipe;

import com.example.RecipesAPI.exception.RecipeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeModelAssembler recipeModelAssembler;

    public CollectionModel<EntityModel<Recipe>> getAllRecipe() {
        List<EntityModel<Recipe>> entityModels = recipeRepository.findAll().stream()
                .map(recipeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(RecipeController.class).getAllRecipe()).withSelfRel());
    }

    public EntityModel<Recipe> getRecipeById(String id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        return recipeModelAssembler.toModel(recipe);
    }

    public CollectionModel<EntityModel<Recipe>> getRecipeByName(String name) {
        List<EntityModel<Recipe>> entityModels = recipeRepository.findByName(name)
                .orElseThrow(RecipeNotFoundException::new)
                .stream()
                .map(recipeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(RecipeController.class).getRecipeByName(name)).withSelfRel());
    }

    public CollectionModel<EntityModel<Recipe>> getRecipeByIngredients(String ingredients) {
        List<EntityModel<Recipe>> entityModels = recipeRepository.findAllByIngredientsContains(ingredients)
                .orElseThrow(RecipeNotFoundException::new)
                .stream()
                .map(recipeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(RecipeController.class).getRecipeByIngredients(ingredients)).withSelfRel());
    }

    public ResponseEntity<?> addRecipe(Recipe recipe) {
        EntityModel<Recipe> recipeEntityModel =
                recipeModelAssembler.toModel(recipeRepository.save(recipe));

        return ResponseEntity.created(recipeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(recipeEntityModel);
    }

    public ResponseEntity<?> updateRecipe(String id, Recipe newRecipe) {
        Recipe updatedRecipe = recipeRepository.findById(id).
                map(recipe -> {
                    recipe.setName(newRecipe.getName());
                    recipe.setDescription(newRecipe.getDescription());
                    recipe.setIngredients(newRecipe.getIngredients());
                    recipe.setDirections(newRecipe.getDirections());
                    return recipeRepository.save(recipe);
                })
                .orElseGet(() -> {
                    newRecipe.setId(id);
                    return recipeRepository.save(newRecipe);
                });

        EntityModel<Recipe> entityModel = recipeModelAssembler.toModel(updatedRecipe);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> deleteRecipe(String id) {
        recipeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
