package com.example.owner.itinerick3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActivityLocation extends AppCompatActivity {
    private Spinner spinner1;
    private Button btnAdd;
    private Button btnCompute;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        myDataset = new ArrayList<String>();
        myDataset.add("empty");
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Context c = getApplicationContext();
        mAdapter = new MyAdapter(c,myDataset);
        mRecyclerView.setAdapter(mAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
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

    //Compute Itinerary - goes to the Map Fragment where the shortest path is shown
    public void onClickCompute(View v){
        Intent intent = new Intent(this, FragmentMap.class);
        intent.putExtra("listOfPlaces", myDataset);
        startActivity(intent);
    }
}
