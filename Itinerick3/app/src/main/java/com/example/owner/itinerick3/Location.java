package com.example.owner.itinerick3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class Location extends AppCompatActivity {
    private Spinner spinner1;
    private Button btnSubmit;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int[] display = {
            R.drawable.buddha,
            R.drawable.gardens,
            R.drawable.hawpar,
            R.drawable.infinity,
            R.drawable.merlion,
            R.drawable.mustafa,
            R.drawable.umbrella,
            R.drawable.safari,
            R.drawable.uss};
    private static final int[] text = {
            R.string.buddha,
            R.string.gardens,
            R.string.hawpar,
            R.string.infinity,
            R.string.merlion,
            R.string.mustafa,
            R.string.umbrella,
            R.string.safari,
            R.string.uss};
    private ArrayList<String> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bighead);

        myDataset = new ArrayList<String>();
        myDataset.add("empty");
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Context c = getApplicationContext();
        mAdapter = new MyAdapter(c,myDataset);
        mRecyclerView.setAdapter(mAdapter);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = String.valueOf(spinner1.getSelectedItem());
                if (myDataset.contains("empty")){
                    myDataset.remove("empty");
                }
                if (!myDataset.contains(location)){
                    myDataset.add(location);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
