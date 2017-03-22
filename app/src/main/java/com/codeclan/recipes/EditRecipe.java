package com.codeclan.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditRecipe extends AppCompatActivity {

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
        EditText editRecipeName = (EditText) findViewById(R.id.edit_recipe_name);
        editRecipeName.setText(recipeName);

        EditText editRecipeMethod = (EditText) findViewById(R.id.edit_method);
        editRecipeMethod.setText(recipeMethod);

        EditText editRecipeIngredients = (EditText) findViewById(R.id.edit_ingredients);
        editRecipeIngredients.setText(recipeIngredients);
    }

    public void onEditButtonClicked(View view){
//        TODO get saved preferences arraylist of recipes



//        TODO delete item we are editing



//        TODO save data we just edited back to shared prefs.
    }

}
