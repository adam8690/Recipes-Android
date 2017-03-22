package com.codeclan.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adam Baxter on 21/03/2017.
 */

public class RecipesAdapter extends ArrayAdapter<Recipe>{

    public RecipesAdapter(Context context, ArrayList<Recipe> recipes){
        super(context, 0, recipes);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_item, parent, false);
        }

        Recipe currentRecipe = getItem(position);

        TextView recipeName = (TextView)listItemView.findViewById(R.id.recipe_name);
        recipeName.setText(currentRecipe.getName());
        recipeName.setTag(currentRecipe);

        ImageView recipeImage = (ImageView)listItemView.findViewById(R.id.thumbnail);
        recipeImage.setImageResource(currentRecipe.getPhotoId());
        recipeImage.setTag(currentRecipe);

        return listItemView;
    }

}
