package com.trile.buzzapizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textViewGreeting;

    ListView listViewHistoryOrders;
    List<HistoryOrderItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGreeting = findViewById(R.id.text_view_greeting);

        listViewHistoryOrders = findViewById(R.id.list_view_history_orders);

        setupTextViewGreeting();
        setupHistoryOrdersListView();
    }

    private void setupTextViewGreeting() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        String[] dayTime = getResources().getStringArray(R.array.day_time);
        String greeting = getResources().getString(R.string.greeting);

        String dayTimeToShow;
        if (hour >= 6) {
            dayTimeToShow = String.format(greeting, dayTime[2]);
        } else if (hour >= 12) {
            dayTimeToShow = String.format(greeting, dayTime[1]);
        } else if (hour >= 6) {
            dayTimeToShow = String.format(greeting, dayTime[0]);
        } else {
            dayTimeToShow = String.format(greeting, dayTime[3]);
        }
        textViewGreeting.setText(dayTimeToShow);
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