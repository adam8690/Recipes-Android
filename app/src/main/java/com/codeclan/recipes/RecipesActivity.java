package com.codeclan.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity {

    ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipes_list);

        initializeData();

        RecipesAdapter recipesAdapter = new RecipesAdapter(this, recipes);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(recipesAdapter);

    }

    public void initializeData(){
        ArrayList<Ingredient> ingreds = new ArrayList<Ingredient>();



        ingreds.add(new Ingredient("thing"));
        ingreds.add(new Ingredient("thing2"));


        recipes.add(new Recipe("Test Recipe 1", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 2", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 3", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 4", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 5", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 6", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 7", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 8", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 9", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 10", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 11", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 12", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 13", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
        recipes.add(new Recipe("Test Recipe 14", "do stuff", ingreds, R.mipmap.recipe_placeholder_colour));
    }


    public void nameClicked(View textView){


        Recipe r = (Recipe)textView.getTag();

        Log.d("Name clicked", r.getName());
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra("recipeName", r.getName());
        intent.putExtra("recipeMethod", r.getMethod());
        intent.putExtra("recipeIngredients", r.getIngredients());
        //made ingredient class serializable so it could be passed in extras. May need to cast ingredients back to serializable when I get them back in details activity.
        startActivity(intent);
    }



}
