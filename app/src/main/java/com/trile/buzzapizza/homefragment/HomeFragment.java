package com.trile.buzzapizza.homefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String HISTORY_ORDER_ITEMS = "HISTORY_ORDER_ITEMS";

    private TextView textViewGreeting;

    private ListView listViewHistoryOrders;
    private List<HistoryOrderItem> historyOrderItems = new ArrayList();

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param items List of history order items.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(List<HistoryOrderItem> items) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(HISTORY_ORDER_ITEMS, (ArrayList<HistoryOrderItem>) items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            historyOrderItems = getArguments().getParcelableArrayList(HISTORY_ORDER_ITEMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textViewGreeting = view.findViewById(R.id.text_view_greeting);

        listViewHistoryOrders = view.findViewById(R.id.list_view_history_orders);

        setupTextViewGreeting();
        setupHistoryOrdersListView();

        return view;
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
        // Show greeting based on current day time
        textViewGreeting.setText(dayTimeToShow);
    }

    private void setupHistoryOrdersListView() {
        listViewHistoryOrders.setAdapter(new HistoryOrderListAdapter(getActivity(), historyOrderItems));
    }
}