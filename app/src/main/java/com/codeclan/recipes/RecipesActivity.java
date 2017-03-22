package com.codeclan.recipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.codeclan.recipes.AddRecipe.RECIPES;

public class RecipesActivity extends AppCompatActivity {

    ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipes_list);


//      get existing list of recipes from shared prefs or create if doesn't exist
        SharedPreferences sharedPref = getSharedPreferences(RECIPES, Context.MODE_PRIVATE);

//        get arraylist of saved recipes from shared preferences.
        String savedRecipes = sharedPref.getString("mySavedRecipes", new ArrayList<Recipe>().toString());
        Log.d("STUFFF", savedRecipes);
        Gson gson = new Gson();

//        convert returned string to Arraylist of recipes
        TypeToken<ArrayList<Recipe>> recipeArrayList = new TypeToken<ArrayList<Recipe>>(){};
        recipes = gson.fromJson(savedRecipes, recipeArrayList.getType());


//        pass recipes arraylist from saved preferences to adapter to create listview
        RecipesAdapter recipesAdapter = new RecipesAdapter(this, recipes);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(recipesAdapter);

        //          Creating AppBar and toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //        register list items for context menu for recipes
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        position of item in list. Staring at zero pass this to get the recipe object from the arraylist.

        int index = info.position;
        Recipe selectedRecipe = recipes.get(index);


        Log.d("context item pos:", String.valueOf(index));

        switch (item.getItemId()){
            case R.id.delete:
//                Get recipe being selected


//                use getName to check correct recipe is being targeted for deletion.
            Log.d("Recipe for deletion:", selectedRecipe.getName());

//            add delete stuff here don't know if the correct recipe object can be passed here yet.
//                get saved prefs arraylist

                //      get existing list of recipes from shared prefs or create if doesn't exist
                SharedPreferences sharedPref = getSharedPreferences(RECIPES, Context.MODE_PRIVATE);

                String savedRecipes = sharedPref.getString("mySavedRecipes", new ArrayList<Recipe>().toString());
                Gson gson = new Gson();

//        convert returned string to Arraylist of recipes
                TypeToken<ArrayList<Recipe>> recipeArrayList = new TypeToken<ArrayList<Recipe>>(){};
                recipes = gson.fromJson(savedRecipes, recipeArrayList.getType());

//                delete from index
                recipes.remove(index);

//                save arraylist minus deleted recipe to saved prefs.
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("mySavedRecipes", gson.toJson(recipes));
                editor.apply();

//              open new recipes list screen to refresh list once item has been deleted.
                Intent deleteIntent = new Intent(this, RecipesActivity.class);
                startActivity(deleteIntent);
                Toast.makeText(this, "Recipe Deleted!", Toast.LENGTH_LONG).show();

            return true;
            case R.id.edit:
            Log.d("Context Item Selected:", "edit");
//            add intent to edit view here. Likewise with the recipe object.


                Intent editIntent = new Intent(this, EditRecipe.class);
                editIntent.putExtra("name", selectedRecipe.getName());
                editIntent.putExtra("method", selectedRecipe.getMethod());
                ArrayList<Ingredient> ingredients = selectedRecipe.getIngredients();
//                TODO create method to convert ingredients to a string with each separated by a comma then pass this as an extra also
//                convertIngredientsArrayListToCommaSeparatedString(ingredients);
//                editIntent.putExtra("ingredients", ingredientsCommaString);
                startActivity(editIntent);

            return true;
            default:
                return super.onContextItemSelected(item);
        }
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
