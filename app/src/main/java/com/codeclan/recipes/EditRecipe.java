package com.codeclan.recipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.codeclan.recipes.Ingredient.convertListStringToArrayList;

public class EditRecipe extends AppCompatActivity {

    public static final String RECIPES = "savedRecipes";
    EditText editRecipeName;
    EditText editRecipeMethod;
    EditText editRecipeIngredients;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        Intent intent = getIntent();

//        Recipe recipe = getTag(findViewById(R.id.edit_recipe_name));
        String recipeName = intent.getStringExtra("name");
        String recipeMethod = intent.getStringExtra("method");
        String recipeIngredients = intent.getStringExtra("ingredients");


//        get the edit text view so I can prepopulate the strings in the xml.
        editRecipeName = (EditText) findViewById(R.id.edit_recipe_name);
        editRecipeName.setText(recipeName);

        editRecipeMethod = (EditText) findViewById(R.id.edit_method);
        editRecipeMethod.setText(recipeMethod);

        editRecipeIngredients = (EditText) findViewById(R.id.edit_ingredients);
        editRecipeIngredients.setText(recipeIngredients);
    }

    public void onEditButtonClicked(View view){
//        this intent is passed when context menu is opened and so we can get the arraylist position from it.
        Intent intent = getIntent();
        int arrayListIndex = intent.getIntExtra("position", 0);
//        updated values have to come from Editviews in EditRecipe xml then save them to a recipe object like when saving.
        String newName = editRecipeName.getText().toString();
        String newMethod = editRecipeMethod.getText().toString();
        String newIngredients = editRecipeIngredients.getText().toString();
//        convert ingredients string to arraylist of ingredients.
        ArrayList<Ingredient> newIngredsArray = convertListStringToArrayList(newIngredients);

//        create new recipe object with updated data
        Recipe editedRecipe = new Recipe(newName, newMethod, newIngredsArray, R.drawable.recipe_placeholder);

        //        get existing list of recipes from shared prefs or create if doesn't exist
        SharedPreferences sharedPref = getSharedPreferences(RECIPES, Context.MODE_PRIVATE);

//      second argument is the default value and will be returned if empty. here it is an empty arraylist so new recipes can be added to it.
        String savedRecipes = sharedPref.getString("mySavedRecipes", new ArrayList<Recipe>().toString());
        Gson gson = new Gson();

//        convert returned string to Arraylist of recipes
        TypeToken<ArrayList<Recipe>> recipeArrayList = new TypeToken<ArrayList<Recipe>>(){};
        ArrayList<Recipe> myRecipes = gson.fromJson(savedRecipes, recipeArrayList.getType());


//      replace item at position in arraylist with new recipe object.

        myRecipes.set(arrayListIndex, editedRecipe);


        Log.d("Recipe edited", myRecipes.get(arrayListIndex).getName());
//        myRecipes.set(arrayListIndex, editedRecipe);

//   save data we just edited back to shared prefs.

//   pass this object to shared preferences to be saved
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("mySavedRecipes", gson.toJson(myRecipes));
        editor.apply();


        Toast.makeText(this, "Recipe Edited!", Toast.LENGTH_LONG).show();

//        take user back to recipes list.
        Intent listIntent = new Intent(this, RecipesActivity.class);
        startActivity(listIntent);

    }

}
