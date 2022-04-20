package com.trile.buzzapizza;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trile.buzzapizza.homefragment.HistoryOrderItem;
import com.trile.buzzapizza.homefragment.HomeFragment;
import com.trile.buzzapizza.interfaces.FragmentAction;
import com.trile.buzzapizza.interfaces.FragmentCommunicator;
import com.trile.buzzapizza.orderfragment.OrderFragment;
import com.trile.buzzapizza.toppingsfragment.ToppingsFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment homeFragment = HomeFragment.newInstance(getMockData());

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, homeFragment);
        fragmentTransaction.commit();
    }

    private List<HistoryOrderItem> getMockData() {
        List<HistoryOrderItem> items = new ArrayList();
        items.add(new HistoryOrderItem(
                Arrays.asList("tomatoes", "mushrooms", "olives", "Jalapenos"))
        );
        items.add(new HistoryOrderItem(
                Arrays.asList("onions", "Pepperoni", "tomatoes"))
        );
        items.add(new HistoryOrderItem(
                Arrays.asList("Mushrooms", "bacon", "green peppers", "olives"))
        );
        items.add(new HistoryOrderItem(
                Arrays.asList("bacon", "Green peppers", "pepperoni", "olives"))
        );
        return items;
    }

    @Override
    public void takeAction(FragmentAction action, String jsonData) {
        switch (action) {
            case CUSTOMIZE_PIZZA:
                onClickCustomizePizza();
                break;
            case UPDATE_HISTORY_ORDER:
            case BACK_SELECT_TOPPINGS:
                onNavigateToSelectToppingsPage(jsonData);
                break;
            case BACK_HOME_PAGE:
            case CANCEL:
                onClickBackHomePage();
                break;
            case NEXT_FILL_ORDER_INFO:
                onClickNextFillOrderInfo(jsonData);
                break;
            case PROCEED_ORDER:
                break;
            default:
                break;
        }
    }

    public void onClickCustomizePizza() {
        Fragment toppingsFragment = new ToppingsFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, toppingsFragment);
        fragmentTransaction.commit();
    }

    private void onNavigateToSelectToppingsPage(String jsonData) {
        Type toppingListType = new TypeToken<ArrayList<String>>() {}.getType();
        Gson gson = new Gson();
        ArrayList<String> selectedToppings = gson.fromJson(jsonData, toppingListType);

        Fragment toppingsFragment = ToppingsFragment.newInstance(selectedToppings);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, toppingsFragment);
        fragmentTransaction.commit();
    }

    private void onClickBackHomePage() {
        Fragment homeFragment = HomeFragment.newInstance(getMockData());

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, homeFragment);
        fragmentTransaction.commit();
    }

    private void onClickNextFillOrderInfo(String jsonData) {
        Type toppingListType = new TypeToken<ArrayList<String>>() {}.getType();
        Gson gson = new Gson();
        ArrayList<String> selectedToppings = gson.fromJson(jsonData, toppingListType);

        Fragment orderFragment = OrderFragment.newInstance(selectedToppings);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, orderFragment);
        fragmentTransaction.commit();
    }
}