package com.example.RecipesAPI.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recipe")
public class Recipe {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> directions;
}
