package com.trile.buzzapizza.orderfragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.Arrays;
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

    private ImageView btnCancel;

    private TextInputEditText editTextName;
    private TextInputEditText editTextAddress;
    private TextInputEditText editTextCity;
    private TextInputEditText editTextZipCode;
    private List<Boolean> textFieldsFilled = new ArrayList<>(
            Arrays.asList(false, false, false, false)
    );

    private Button btnBack;
    private Button btnNext;

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

        btnCancel = view.findViewById(R.id.btn_cancel);

        editTextName = view.findViewById(R.id.edit_text_name);
        editTextAddress = view.findViewById(R.id.edit_text_address);
        editTextCity = view.findViewById(R.id.edit_text_city);
        editTextZipCode = view.findViewById(R.id.edit_text_zip_code);

        btnBack = view.findViewById(R.id.btn_back);
        btnNext = view.findViewById(R.id.btn_next);

        setupEditText(editTextName, 0);
        setupEditText(editTextAddress, 1);
        setupEditText(editTextCity, 2);
        setupEditText(editTextZipCode, 3);

        return view;
    }

    private void setupEditText(TextInputEditText editText, int position) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // If this field has text AND all other fields are filled
                if (!editable.toString().trim().isEmpty()) {
                    textFieldsFilled.set(position, true);
                    if (!textFieldsFilled.contains(false)) {
                        btnOrder.setEnabled(true);
                    }
                } else {
                    textFieldsFilled.set(position, false);
                    btnOrder.setEnabled(false);
                }
            }
        });
        editText.setOnFocusChangeListener((view, focused) -> {
            TextInputEditText et = (TextInputEditText) view;
            // If field has lost focused, but still empty => Show error
            if (!focused && et.getText().toString().isEmpty()) {
                et.setError(getResources().getString(R.string.field_required));
            }
        });
    }
}