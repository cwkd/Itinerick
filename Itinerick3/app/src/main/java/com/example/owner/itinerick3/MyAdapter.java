package com.example.owner.itinerick3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by Owner on 11/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    private HashMap<String, String> data;
    Context parentContext;

    // Initialize dataset, taking in parent context to initialize package name in bind method
    public MyAdapter(Context c,ArrayList myDataset) {
        mDataset = myDataset;
        parentContext = c;
    }

    //View holder class that takes our recycler view item holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public ImageView pic;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_text);
            pic = view.findViewById(R.id.item_image);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ActivityShowDetails.class);
            String text = name.getText().toString();
            String keyword = data.get(text);
            intent.putExtra("name",keyword);
            view.getContext().startActivity(intent);
        }
    }



    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        data = new HashMap<>();
        data.put("Buddha Tooth","buddha");
        data.put("Gardens by the Bay","gardens");
        data.put("Haw Par Villa","hawpar");
        data.put("Infinity Pool","infinity");
        data.put("Merlion","merlion");
        data.put("Mustafa", "mustafa");
        data.put("Umbrella Trees","umbrella");
        data.put("Night Safari","safari");
        data.put("Universal Studios","uss");
        data.put("empty","empty");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mDataset.get(position);
        String keyword = data.get(name);
        String packageName = parentContext.getPackageName();
        String typeOfResource = "drawable";
        int resID = parentContext.getResources().getIdentifier(keyword, typeOfResource, packageName);
        holder.pic.setImageResource(resID);
        holder.name.setText(name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
