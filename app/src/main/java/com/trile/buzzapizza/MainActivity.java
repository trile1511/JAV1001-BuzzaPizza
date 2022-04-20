package com.trile.buzzapizza;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {

    private static final String HISTORY_ORDER_ITEM_LIST = "HISTORY_ORDER_ITEM_LIST";

    Gson gson = new Gson();
    List<HistoryOrderItem> historyOrderItemList = new ArrayList<>();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String savedRawList = sharedPreferences.getString(HISTORY_ORDER_ITEM_LIST, "");

        Fragment homeFragment;
        if (!savedRawList.isEmpty()) {
            Type listType = new TypeToken<List<HistoryOrderItem>>() {
            }.getType();
            historyOrderItemList = gson.fromJson(savedRawList, listType);

            homeFragment = HomeFragment.newInstance(historyOrderItemList);
        } else {
            homeFragment = new HomeFragment();
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

        editor = sharedPreferences.edit();

        editor.putString(HISTORY_ORDER_ITEM_LIST, gson.toJson(historyOrderItemList));
        editor.apply();
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
            case REMOVE_HISTORY_ORDER:
                onClickRemoveHistoryOrder(jsonData);
                break;
            case BACK_HOME_PAGE:
            case CANCEL:
                onClickBackHomePage();
                break;
            case NEXT_FILL_ORDER_INFO:
                onClickNextFillOrderInfo(jsonData);
                break;
            case PROCEED_ORDER:
                onClickProceedOrder(jsonData);
                break;
            default:
                break;
        }
    }

    public void onClickCustomizePizza() {
        Fragment toppingsFragment = new ToppingsFragment();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, toppingsFragment);
        fragmentTransaction.commit();
    }

    private void onNavigateToSelectToppingsPage(String jsonData) {
        Gson gson = new Gson();
        HistoryOrderItem historyOrderItem = gson.fromJson(jsonData, HistoryOrderItem.class);

        Fragment toppingsFragment = ToppingsFragment.newInstance(historyOrderItem);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, toppingsFragment);
        fragmentTransaction.commit();
    }

    private void onClickRemoveHistoryOrder(String removedPosition) {
        try {
            int position = Integer.parseInt(removedPosition);
            historyOrderItemList.remove(position);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(MainActivity.class.getCanonicalName(), "onClickRemoveHistoryOrder: e = ", e);
        }
    }

    private void onClickBackHomePage() {
        Fragment homeFragment = HomeFragment.newInstance(historyOrderItemList);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, homeFragment);
        fragmentTransaction.commit();
    }

    private void onClickNextFillOrderInfo(String jsonData) {
        Gson gson = new Gson();
        HistoryOrderItem historyOrderItem = gson.fromJson(jsonData, HistoryOrderItem.class);

        Fragment orderFragment = OrderFragment.newInstance(historyOrderItem);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, orderFragment);
        fragmentTransaction.commit();
    }

    private void onClickProceedOrder(String jsonData) {
        Toast.makeText(this, "Order Successfully!", Toast.LENGTH_LONG).show();

        HistoryOrderItem historyOrderItem = gson.fromJson(jsonData, HistoryOrderItem.class);

        int matchedIndex = historyOrderItemList.indexOf(historyOrderItem);
        if (matchedIndex == -1) {
            historyOrderItemList.add(historyOrderItem);
        } else {
            historyOrderItemList.set(matchedIndex, historyOrderItem);
        }

        Fragment homeFragment = HomeFragment.newInstance(historyOrderItemList);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main, homeFragment);
        fragmentTransaction.commit();
    }
}