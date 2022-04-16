package com.trile.buzzapizza;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment homeFragment = HomeFragment.newInstance(getMockData());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout_main, homeFragment);
        ft.commit();
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
}