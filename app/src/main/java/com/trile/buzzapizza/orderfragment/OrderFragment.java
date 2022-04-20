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
import com.google.gson.Gson;
import com.trile.buzzapizza.R;
import com.trile.buzzapizza.homefragment.HistoryOrderItem;
import com.trile.buzzapizza.interfaces.FragmentAction;
import com.trile.buzzapizza.interfaces.FragmentCommunicator;

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
    private static final String HISTORY_ORDER_ITEM = "HISTORY_ORDER_ITEM";
    private final List<Boolean> textFieldsFilled = new ArrayList<>(
            Arrays.asList(false, false, false, false)
    );
    private HistoryOrderItem historyOrderItem;

    private ImageView btnCancel;

    private TextInputEditText editTextName;
    private TextInputEditText editTextAddress;
    private TextInputEditText editTextCity;
    private TextInputEditText editTextZipCode;
    private List<String> selectedToppings = new ArrayList<>();

    private Button btnBack;
    private Button btnOrder;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param historyOrderItem history order item to keep track through navigation
     *                         from home page until order page.
     * @return A new instance of fragment NavigationFragment.
     */
    public static OrderFragment newInstance(HistoryOrderItem historyOrderItem) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putParcelable(HISTORY_ORDER_ITEM, historyOrderItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            historyOrderItem = getArguments().getParcelable(HISTORY_ORDER_ITEM);
            selectedToppings = historyOrderItem.getToppings();
        } else {
            historyOrderItem = new HistoryOrderItem(selectedToppings);
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
        btnOrder = view.findViewById(R.id.btn_order);

        setupButtonCancel();

        setupEditText(editTextName, 0);
        setupEditText(editTextAddress, 1);
        setupEditText(editTextCity, 2);
        setupEditText(editTextZipCode, 3);

        editTextName.setText(historyOrderItem.getName());
        editTextAddress.setText(historyOrderItem.getAddress());
        editTextCity.setText(historyOrderItem.getCity());
        editTextZipCode.setText(historyOrderItem.getZipCode());

        setupButtonBack();

        setupButtonOrder();

        return view;
    }

    private void setupButtonCancel() {
        btnCancel.setOnClickListener(view -> {
            FragmentCommunicator fc = (FragmentCommunicator) getActivity();
            if (fc != null) {
                fc.takeAction(FragmentAction.CANCEL, null);
            }
        });
    }

    private void setupEditText(TextInputEditText editText, int position) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // If this field has text
                if (!editable.toString().trim().isEmpty()) {
                    textFieldsFilled.set(position, true);
                    // AND all other fields are filled
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
            // If field has lost focused
            if (!focused) {
                // But still empty => Show error
                if (et.getText() != null && et.getText().toString().isEmpty()) {
                    et.setError(getResources().getString(R.string.field_required));
                } else {
                    // Field is filled => Save it
                    Editable ed;
                    switch (position) {
                        case 0:
                            ed = editTextName.getText();
                            if (ed != null) {
                                historyOrderItem.setName(ed.toString());
                            }
                            break;
                        case 1:
                            ed = editTextAddress.getText();
                            if (ed != null) {
                                historyOrderItem.setAddress(ed.toString());
                            }
                            break;
                        case 2:
                            ed = editTextCity.getText();
                            if (ed != null) {
                                historyOrderItem.setCity(ed.toString());
                            }
                            break;
                        case 3:
                            ed = editTextZipCode.getText();
                            if (ed != null) {
                                historyOrderItem.setZipCode(ed.toString());
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private void setupButtonBack() {
        btnBack.setOnClickListener(view -> {
            Gson gson = new Gson();
            FragmentCommunicator fc = (FragmentCommunicator) getActivity();
            if (fc != null) {
                fc.takeAction(
                        FragmentAction.BACK_SELECT_TOPPINGS,
                        gson.toJson(historyOrderItem)
                );
            }
        });
    }

    private void setupButtonOrder() {
        btnOrder.setOnClickListener(view -> {
            Gson gson = new Gson();
            FragmentCommunicator fc = (FragmentCommunicator) getActivity();
            if (fc != null) {
                fc.takeAction(
                        FragmentAction.PROCEED_ORDER,
                        gson.toJson(historyOrderItem)
                );
            }
        });
    }
}