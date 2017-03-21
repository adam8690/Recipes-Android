package com.codeclan.recipes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adam Baxter on 21/03/2017.
 */

public class RecipeTest {

    private Recipe recipe;
    private ArrayList<Ingredient> list;
    private Ingredient ingredient;
    private ArrayList<Ingredient> list2;


    @Before
    public void before(){
        list = new ArrayList<Ingredient>();
        ingredient = new Ingredient("bread");
        list2 = new ArrayList<Ingredient>();
        list.add(ingredient);
        list2.add(ingredient);
        list2.add(ingredient);
        recipe = new Recipe("Toast", "put it in a toaster", list, 1);
    }

    @Test
    public void testRecipeHasIngredient(){
        assertEquals(recipe.getIngredients().get(0).getName(), "bread");
    }

    @Test
    public void testCanAddListToRecipeIngredients(){
        recipe.addListOfIngredients(list2);
        assertEquals(recipe.getIngredients().size(), 3);
    }
}
