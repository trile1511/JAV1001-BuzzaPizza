package com.trile.buzzapizza;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.trile.buzzapizza.homefragment.HistoryOrderItem;
import com.trile.buzzapizza.homefragment.HomeFragment;
import com.trile.buzzapizza.interfaces.FragmentAction;
import com.trile.buzzapizza.interfaces.FragmentCommunicator;
import com.trile.buzzapizza.toppingsfragment.ToppingsFragment;

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
                Arrays.asList("mozzarella", "BBQ sauce", "tomato", "mushrooms", "olives"))
        );
        items.add(new HistoryOrderItem(
                Arrays.asList("mozzarella", "basil pesto sauce", "a blend of healthy veggies"))
        );
        items.add(new HistoryOrderItem(
                Arrays.asList("classic crust", "mozzarella", "tomato sauce", "double pepperoni"))
        );
        return items;
    }

    @Override
    public void takeAction(FragmentAction action) {
        switch (action) {
            case CUSTOMIZE_PIZZA:
                onClickCustomizePizza();
                break;
            case UPDATE_HISTORY_ORDER:
                break;
            case BACK_HOME_PAGE:
                break;
            case NEXT_FILL_ORDER_INFO:
                break;
            case BACK_SELECT_TOPPINGS:
                break;
            case PROCEED_ORDER:
                break;
            case CANCEL:
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
}