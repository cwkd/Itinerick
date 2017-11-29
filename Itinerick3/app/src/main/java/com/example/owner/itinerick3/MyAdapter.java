package com.example.owner.itinerick3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Owner on 11/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    Context parentContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView pic;
        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.item_text);
            pic = v.findViewById(R.id.item_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context c,ArrayList myDataset) {
        mDataset = myDataset;
        parentContext = c;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mDataset.get(position);
        String packageName = parentContext.getPackageName();
        String typeOfResource = "drawable";
        int resID = parentContext.getResources().getIdentifier(name, typeOfResource, packageName);
        holder.pic.setImageResource(resID);
        holder.name.setText(name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;
    }
}
