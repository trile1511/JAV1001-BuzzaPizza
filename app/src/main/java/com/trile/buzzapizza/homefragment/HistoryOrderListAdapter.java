package com.trile.buzzapizza.homefragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.List;

class HistoryOrderListAdapter extends BaseAdapter {
    private final Context context;
    private final LayoutInflater inflater;

    private final List<HistoryOrderItem> items = new ArrayList();
    private boolean isBtnRemoveVisible = false;

    public HistoryOrderListAdapter(Context context, List<HistoryOrderItem> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items.addAll(items);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_main_history_order_list_item, null);
            holder = new ViewHolder();
            holder.toppingsView = view.findViewById(R.id.toppings);
            holder.btnRemove = view.findViewById(R.id.btn_remove);
            holder.btnRemove.setOnClickListener(v -> onRemoveOrder(i));
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (!isBtnRemoveVisible && holder.btnRemove.getVisibility() != View.GONE) {
            holder.btnRemove.setVisibility(View.GONE);
        } else if (isBtnRemoveVisible && holder.btnRemove.getVisibility() != View.VISIBLE) {
            holder.btnRemove.setVisibility(View.VISIBLE);
        }
        holder.toppingsView.setText(items.get(i).getToppingsText());
        return view;
    }

    private void onRemoveOrder(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Remove Order")
                .setMessage("Are you sure you want to permanently remove this order?")
                .setNegativeButton("Cancel",
                        (dialog, which) -> dialog.dismiss()
                )
                .setPositiveButton("Confirm",
                        (dialog, which) -> {
                            items.remove(position);
                            notifyDataSetChanged();
                        })
                .show();
    }

    public void toggleBtnRemoveVisibility() {
        this.isBtnRemoveVisible = !this.isBtnRemoveVisible;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView toppingsView;
        ImageView btnRemove;
    }
}
