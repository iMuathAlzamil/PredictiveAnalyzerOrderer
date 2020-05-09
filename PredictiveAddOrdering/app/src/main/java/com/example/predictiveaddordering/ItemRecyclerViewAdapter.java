package com.example.predictiveaddordering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.RecyclerViewHolder>
{
    private ArrayList<Item> rvItems;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView itemsTextView;

        public RecyclerViewHolder(View v) {
            super(v);
            itemsTextView = (TextView)v.findViewById(R.id.items);
        }

        public void setProduct(Item pd) {
            System.out.println(pd.getName());
            itemsTextView.setText(pd.getName());
        }
    }

    public ItemRecyclerViewAdapter(ArrayList<Item> data) {
        rvItems = data;
    }

    @Override
    public ItemRecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vert_list, parent, false);
        return new ItemRecyclerViewAdapter.RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.setProduct(rvItems.get(position));
    }

    @Override
    public int getItemCount() {
        return rvItems.size();
    }
}
