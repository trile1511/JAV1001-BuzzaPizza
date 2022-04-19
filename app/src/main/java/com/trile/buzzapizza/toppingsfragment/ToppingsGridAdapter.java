package com.trile.buzzapizza.toppingsfragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ToppingsGridAdapter extends BaseAdapter {
    private Context context;
    private final LayoutInflater inflater;

    private List<Topping> toppingList = new ArrayList<>();
    private List<Boolean> toppingSelectionStatuses;

    public ToppingsGridAdapter(Context context, List<Topping> toppingList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.toppingList.addAll(toppingList);

        this.toppingSelectionStatuses = new ArrayList<>(
                Arrays.asList(new Boolean[this.toppingList.size()])
        );
        Collections.fill(this.toppingSelectionStatuses, false);
    }

    @Override
    public int getCount() {
        return toppingList.size();
    }

    @Override
    public Object getItem(int i) {
        return toppingList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_toppings_grid_item, viewGroup, false);
            holder = new ViewHolder();
            holder.iconToppingSelectionStatus = view.findViewById(R.id.ic_topping_selection_status);

            holder.toppingImage = view.findViewById(R.id.topping_image);
            holder.toppingName = view.findViewById(R.id.topping_name);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (!toppingSelectionStatuses.get(i)) {
            holder.iconToppingSelectionStatus.setImageResource(R.drawable.ic_add_box);
            holder.iconToppingSelectionStatus.setBackgroundResource(0);
            holder.iconToppingSelectionStatus.clearColorFilter();
        } else {
            holder.iconToppingSelectionStatus.setImageResource(R.drawable.ic_checkmark);
            holder.iconToppingSelectionStatus.setBackgroundResource(R.drawable.bg_added_box);
            holder.iconToppingSelectionStatus.setColorFilter(
                    context.getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP
            );
        }
        holder.toppingImage.setImageResource(toppingList.get(i).getDrawableResourceId());
        holder.toppingName.setText(toppingList.get(i).getName());
        return view;
    }

    public Boolean onClickToppingCard(int position) {
        Boolean currentSelectionStatus = toppingSelectionStatuses.get(position);
        toppingSelectionStatuses.set(position, !currentSelectionStatus);
        notifyDataSetChanged();

        // Specify whether there is no topping selected to enable Next button or not
        return toppingSelectionStatuses.indexOf(true) == - 1;
    }

    public void setSelectedToppings(List<String> selectedToppings) {
        Collections.fill(this.toppingSelectionStatuses, false);

        String tp;
        int itemIndex;
        for (int i = 0; i < selectedToppings.size(); i++) {
            tp = selectedToppings.get(i);
            itemIndex = toppingList.indexOf(new Topping(tp));
            if (itemIndex != -1) {
                toppingSelectionStatuses.set(itemIndex, true);
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView iconToppingSelectionStatus;
        ImageView toppingImage;
        TextView toppingName;
    }
}
