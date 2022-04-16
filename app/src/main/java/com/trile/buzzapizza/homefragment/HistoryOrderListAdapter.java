package com.trile.buzzapizza.homefragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.trile.buzzapizza.R;

import java.util.ArrayList;
import java.util.List;

class HistoryOrderListAdapter extends BaseAdapter {
    private List<HistoryOrderItem> items = new ArrayList();
    private LayoutInflater inflater;

    public HistoryOrderListAdapter(Context context, List<HistoryOrderItem> items) {
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
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.toppingsView.setText(items.get(i).getToppingsText());
        return view;
    }

    static class ViewHolder {
        TextView toppingsView;
    }
}
