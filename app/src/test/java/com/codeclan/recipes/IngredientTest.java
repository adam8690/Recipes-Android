package com.codeclan.recipes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.codeclan.recipes.Ingredient.convertArrayListToCommaSeparatedString;
import static com.codeclan.recipes.Ingredient.convertListStringToArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Adam Baxter on 21/03/2017.
 */

public class IngredientTest {

    private Ingredient ingredient;
    private String ingredientString = "bread, butter, milk";

    @Before
    public void before() {
        ingredient = new Ingredient("Bread");

    }

    @Test
    public void testIngredientHasName(){
        assertEquals("Bread", ingredient.getName());
    }

    @Test
    public void testIngredientStringConvertedToArrayList(){
        ArrayList<Ingredient> ary = convertListStringToArrayList(ingredientString);
        assertEquals("butter", ary.get(1).getName());
    }

    @Test
    public void testConvertArrayListToCommaSepString(){
//        test empty array
        ArrayList<Ingredient> ingreds = new ArrayList<Ingredient>();
        assertEquals("", convertArrayListToCommaSeparatedString(ingreds));
//        test array with one item
        ingreds.add(ingredient);
        assertEquals("Bread", convertArrayListToCommaSeparatedString(ingreds));
//        test array with multiple items
        ingreds.add(ingredient);
        ingreds.add(ingredient);
        ingreds.add(ingredient);
        assertEquals("Bread, Bread, Bread, Bread", convertArrayListToCommaSeparatedString(ingreds));
    }

}
