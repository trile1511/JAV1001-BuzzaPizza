package com.trile.buzzapizza.toppingsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.trile.buzzapizza.R;
import com.trile.buzzapizza.interfaces.FragmentAction;
import com.trile.buzzapizza.interfaces.FragmentCommunicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToppingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToppingsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TOPPINGS = "TOPPINGS";

    private static final List<Topping> PREDEFINED_TOPPINGS = new ArrayList<>(
            Arrays.asList(
                    new Topping("Pepperoni"),
                    new Topping("Bacon"),
                    new Topping("Mushrooms"),
                    new Topping("Tomatoes"),
                    new Topping("Olives"),
                    new Topping("Green peppers"),
                    new Topping("Onions"),
                    new Topping("Jalapenos")
            )
    );

    private List<String> selectedToppings = new ArrayList<>();

    private ImageView btnCancel;

    private GridView gridViewToppings;
    private ToppingsGridAdapter toppingsGridAdapter;

    private Button btnBack;
    private Button btnNext;

    public ToppingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedTopping selected topping items to be checked on grid view.
     * @return A new instance of fragment ToppingsFragment.
     */
    public static ToppingsFragment newInstance(ArrayList<String> selectedTopping) {
        ToppingsFragment fragment = new ToppingsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(TOPPINGS, selectedTopping);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedToppings = getArguments().getStringArrayList(TOPPINGS);
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

        setupGridViewToppings();

        return view;
    }

    private void setupGridViewToppings() {
        toppingsGridAdapter = new ToppingsGridAdapter(getActivity(), PREDEFINED_TOPPINGS);
        gridViewToppings.setAdapter(toppingsGridAdapter);
        gridViewToppings.setOnItemClickListener((adapterView, view, i, l) -> {
            toppingsGridAdapter.onClickToppingCard(i);
        });

        toppingsGridAdapter.setSelectedToppings(selectedToppings);
    }
}