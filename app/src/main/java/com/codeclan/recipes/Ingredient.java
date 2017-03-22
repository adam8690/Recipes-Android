package com.codeclan.recipes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam Baxter on 21/03/2017.
 */

public class Ingredient implements Serializable{

    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Ingredient> convertListStringToArrayList(String ingredientsList){
        List<String> list = new ArrayList<String>(Arrays.asList(ingredientsList.split(",")));
//        for item in list arraylist. create new ingredient and add to arraylist;

        ArrayList<Ingredient> ingredsArrayList = new ArrayList<Ingredient>();
        String string;
        for (int i = 0; i < list.size(); i++ )
        {
            string = list.get(i);
            ingredsArrayList.add(new Ingredient(string.trim()));
        }

        return ingredsArrayList;

    }

    public static String convertArrayListToCommaSeparatedString(ArrayList<Ingredient> ingredients) {
        String ingredientString;
        String string;

        if (ingredients.size() == 0){ return ""; }
        else {
            string = ingredients.get(0).getName().toString();

            for (int i = 1; i < ingredients.size();
                 i++) {
                ingredientString = ingredients.get(i).getName().toString();
                string += ", " + ingredientString;
            }
        }
    return string;
    }
}
