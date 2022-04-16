package com.trile.buzzapizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewHistoryOrders;
    List<HistoryOrderItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewHistoryOrders = findViewById(R.id.list_view_history_orders);

        setupHistoryOrdersListView();
    }

    private void setupHistoryOrdersListView() {
        items = getMockData();

        listViewHistoryOrders.setAdapter(new HistoryOrderListAdapter(this, items));
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