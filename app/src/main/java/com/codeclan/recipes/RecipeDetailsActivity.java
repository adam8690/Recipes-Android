package com.codeclan.recipes;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

//      getting the extras back that were set in the clicked method of the RecipesActivity class.
        Intent intent = getIntent();
        String name = intent.getStringExtra("recipeName");
        Log.d("name from extras", name);
        String method = intent.getStringExtra("recipeMethod");
        Log.d("method from Extras", method);
// getintextra requires a integer here as a second argument. Inputting zero seems to work?
        int image = intent.getIntExtra("recipeImage", 0);


//        Casting ingredients from serializable back to arraylist.
        Serializable ingreds = intent.getSerializableExtra("recipeIngredients");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) ingreds;

//        Logging
        Ingredient ingredient = ingredients.get(0);
        Log.d("Ingredient:", ingredient.getName());

//        Getting the view represented by the id in the xml so that I can set the text using the recipe object.
        TextView recipeTitle = (TextView) findViewById(R.id.recipe_title);
        recipeTitle.setText(name);

        TextView recipeMethod = (TextView) findViewById(R.id.recipe_method);
        recipeMethod.setText("Method:\n\n" + method);

//converting ingredients arraylist to string for display
        String ingredientsString = createIngredientsString(ingredients);

        TextView recipeIngredients = (TextView) findViewById(R.id.recipe_ingredients);
        recipeIngredients.setText("Ingredients:\n\n" + ingredientsString);
//
        ImageView recipeImage = (ImageView) findViewById(R.id.recipe_image);
        recipeImage.setImageResource(image);

//          Creating AppBar and toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);




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


    public String createIngredientsString(ArrayList<Ingredient> ingredients){

        String list = "";

        for(Ingredient ingred : ingredients){
            list = list + ingred.getName() + " ";
        }
        return list;
    }
}
