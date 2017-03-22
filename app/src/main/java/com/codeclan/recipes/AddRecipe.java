package com.codeclan.recipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.codeclan.recipes.Ingredient.convertListStringToArrayList;

public class AddRecipe extends AppCompatActivity {

    public static final String RECIPES = "savedRecipes";
    ArrayList<Recipe> recipes;
    EditText inputRecipeName;
    EditText inputMethod;
    EditText inputIngredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        //          Creating AppBar and toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        get text from input fields
        inputRecipeName = (EditText) findViewById(R.id.enter_recipe_name);
        inputIngredients = (EditText) findViewById(R.id.enter_ingredients);
        inputMethod = (EditText) findViewById(R.id.enter_method);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;

        switch(item.getItemId()) {
            case R.id.action_add_recipe:
                intent = new Intent(this, AddRecipe.class);
                startActivity(intent);
                return true;

            case R.id.action_list:
                intent = new Intent(this, RecipesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onSaveButtonClicked(View view){
        Log.d("Button pressed", "Save button");

        String name = inputRecipeName.getText().toString();
        String method = inputMethod.getText().toString();
        String ingredients = inputIngredients.getText().toString();

//        need to create arraylist of ingredients. Using my ingredients method.
        ArrayList<Ingredient> ingreds = convertListStringToArrayList(ingredients);


//create new recipe object with input details
        Recipe recipe = new Recipe(name, method, ingreds, R.drawable.recipe_placeholder);
        Log.d("Saved Recipe Object nam", recipe.getName());
        Log.d("Saved Recipe Object met", recipe.getMethod());
        Log.d("Saved Recipe Object ing", recipe.getIngredients().get(0).getName());

//        get existing list of recipes from shared prefs or create if doesn't exist
        SharedPreferences sharedPref = getSharedPreferences(RECIPES, Context.MODE_PRIVATE);

//      second argument is the default value and will be returned if empty.
        String savedRecipes = sharedPref.getString("mySavedRecipes", new ArrayList<Recipe>().toString());
        Log.d("STUFFF", savedRecipes);
        Gson gson = new Gson();

//        convert returned string to Arraylist of recipes
        TypeToken<ArrayList<Recipe>> recipeArrayList = new TypeToken<ArrayList<Recipe>>(){};
        ArrayList<Recipe> myRecipes = gson.fromJson(savedRecipes, recipeArrayList.getType());

//        add new recipe to arraylist and log to see if it gets bigger!
        Log.d("recipes array size:", "" + myRecipes.size());
        myRecipes.add(recipe);
        Log.d("recipes new size:", "" + myRecipes.size());

//   pass this object to shared preferences to be saved
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("mySavedRecipes", gson.toJson(myRecipes));
        editor.apply();


        Toast.makeText(this, "Recipe Saved!", Toast.LENGTH_LONG).show();

//        take user back to recipes list.
        Intent intent = new Intent(this, RecipesActivity.class);
        startActivity(intent);
    }

}
