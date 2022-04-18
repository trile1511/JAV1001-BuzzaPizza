package com.trile.buzzapizza.toppingsfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToppingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToppingsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TOPPINGS = "TOPPINGS";

    private List<Toppings> toppings;

    private ImageView btnCancel;
    private GridView gridViewToppings;
    private Button btnBack;
    private Button btnNext;

    public ToppingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param toppings topping items to show on grid view.
     * @return A new instance of fragment ToppingsFragment.
     */
    public static ToppingsFragment newInstance(List<Toppings> toppings) {
        ToppingsFragment fragment = new ToppingsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(TOPPINGS, (ArrayList<? extends Parcelable>) toppings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            toppings = getArguments().getParcelableArrayList(TOPPINGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toppings, container, false);

        btnCancel = view.findViewById(R.id.btn_cancel);
        gridViewToppings = view.findViewById(R.id.gridview_toppings);
        btnBack = view.findViewById(R.id.btn_back);
        btnNext = view.findViewById(R.id.btn_next);

        return view;
    }
}