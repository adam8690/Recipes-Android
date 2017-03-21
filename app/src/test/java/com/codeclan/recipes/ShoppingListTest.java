package com.codeclan.recipes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adam Baxter on 21/03/2017.
 */

public class ShoppingListTest {

    private ShoppingList shoppingList;
    private Ingredient ingredient1;
    private ArrayList<Ingredient> list;
    private Ingredient ingredient2;

    @Before
    public void before() {

        shoppingList = new ShoppingList(new ArrayList<Ingredient>());
        ingredient1 = new Ingredient("cheese");
        shoppingList.getShoppingList().add(ingredient1);

        list = new ArrayList<Ingredient>();
        ingredient2 = new Ingredient("toast");
        list.add(ingredient2);
        list.add(ingredient2);

    }


    @Test
    public void testCanGetListItemName(){
        assertEquals(shoppingList.getShoppingList().get(0).getName(), "cheese");
    }

    @Test
    public void testCanAddLists(){
        shoppingList.addListToShoppingList(list);
        assertEquals(shoppingList.getShoppingList().size(), 3);
    }



}
