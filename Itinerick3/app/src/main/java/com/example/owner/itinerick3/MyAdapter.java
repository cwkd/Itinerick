package com.example.owner.itinerick3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by Owner on 11/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<String> mDataset;
    Context parentContext;
    ActivityLocation parentAct;
    final String PACKAGE_NAME;
    final String RESOURCE_TYPE = "drawable";

    public interface ViewIdListener {
        public void getViewId(int id);
    }

    // Initialize dataset, taking in parent context to initialize package name in bind method
    public MyAdapter(Context c, ArrayList myDataset, ActivityLocation act) {
        mDataset = myDataset;
        parentContext = c;
        parentAct = act;
        PACKAGE_NAME = parentContext.getPackageName();
    }

    //View holder class that takes our recycler view item holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView name;
        public ImageView pic;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_text);
            pic = view.findViewById(R.id.item_image);
            view.setOnLongClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (name.getText() == "Rick"){
                Toast.makeText(view.getContext(), "Yo! Add some places!", Toast.LENGTH_LONG).show();
            }
            else {
                String text = name.getText().toString();
                parentAct.showDetails(text);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            parentAct.getViewId(name.getId());
            parentAct.showDialog();
            return true;
        }
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
        Log.d("BindViewHolder", name);
        String keyword;
        if (name.equals("Rick")) {
            keyword = "rick";
        } else {
            keyword = parentAct.queryForLocation(name);
        }
        int resID = parentContext.getResources().getIdentifier(keyword, RESOURCE_TYPE, PACKAGE_NAME);
        holder.pic.setImageResource(resID);
        holder.name.setText(name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}


