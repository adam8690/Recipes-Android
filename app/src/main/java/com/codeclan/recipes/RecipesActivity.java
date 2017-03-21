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

        ingreds.add(new Ingredient("Bread"));
        ingreds.add(new Ingredient("Butter"));

        recipes.add(new Recipe("Toast", "Toast bread, spread butter over it.", ingreds, R.drawable.toast));
        recipes.add(new Recipe("Toast2", "Toast bread, spread butter over it.", ingreds, R.drawable.recipe_placeholder));
    }


    public void nameClicked(View textView){


        Recipe r = (Recipe)textView.getTag();

        Log.d("Name clicked", r.getName());
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra("recipeName", r.getName());
        intent.putExtra("recipeMethod", r.getMethod());
        intent.putExtra("recipeIngredients", r.getIngredients());
        intent.putExtra("recipeImage", r.getPhotoId());
        //made ingredient class serializable so it could be passed in extras. May need to cast ingredients back to serializable when I get them back in details activity.
        startActivity(intent);
    }



}
