package com.codeclan.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class AddRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

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

    public void onSaveButtonClicked(View view){
        Log.d("Button pressed", "Save button");

//        Recipe recipe = new Recipe(name, method, ingredients, photoid);



//        take user back to recipes list.
        Intent intent = new Intent(this, RecipesActivity.class);
        startActivity(intent);
    }

}
