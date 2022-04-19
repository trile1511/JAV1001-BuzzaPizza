package com.trile.buzzapizza.orderfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SELECTED_TOPPINGS = "SELECTED_TOPPINGS";

    private List<String> selectedToppings;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedToppings selected topping items to be save on proceeding order.
     * @return A new instance of fragment NavigationFragment.
     */
    public static OrderFragment newInstance(ArrayList<String> selectedToppings) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(SELECTED_TOPPINGS, selectedToppings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedToppings = getArguments().getStringArrayList(SELECTED_TOPPINGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        return view;
    }
}