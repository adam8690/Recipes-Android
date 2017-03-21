package com.codeclan.recipes;

import java.util.ArrayList;

/**
 * Created by Adam Baxter on 21/03/2017.
 */

public class Recipe {

    private String name;
    private String method;
    private ArrayList<Ingredient> ingredients;
    int photoId;

    public Recipe(String name, String method, ArrayList<Ingredient> ingredients, int photoId) {
        this.name = name;
        this.method = method;
        this.ingredients = ingredients;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addListOfIngredients(ArrayList<Ingredient> list) {
        for (Ingredient ingredient : list) {
            this.ingredients.add(ingredient);
        }
    }


}
