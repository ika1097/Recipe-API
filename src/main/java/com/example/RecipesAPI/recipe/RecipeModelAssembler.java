package com.example.RecipesAPI.recipe;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecipeModelAssembler implements RepresentationModelAssembler<Recipe, EntityModel<Recipe>> {
    @NonNull
    @Override
    public EntityModel<Recipe> toModel(@NonNull Recipe recipe) {
        return EntityModel.of(
                recipe,
                linkTo(methodOn(RecipeController.class).getRecipeById(recipe.getId())).withSelfRel(),
                linkTo(methodOn(RecipeController.class).getAllRecipe()).withRel("recipes"));
    }
}
