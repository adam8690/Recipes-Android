package com.codeclan.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intent = getIntent();

        String name = intent.getStringExtra("recipeName");
        Log.d("name from extras", name);
        String method = intent.getStringExtra("recipeMethod");
        Log.d("method from Extras", method);

        Serializable ingreds = intent.getSerializableExtra("recipeIngredients");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) ingreds;

        Ingredient ingredient = ingredients.get(0);

        Log.d("Ingredient:", ingredient.getName());



    }
}
