package com.example.owner.itinerick3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    private LocationDbHelper locationDbHelper;
    private SQLiteDatabase locationDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        myDataset = new ArrayList<String>();
        myDataset.add("Rick");
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Context c = getApplicationContext();
        mAdapter = new MyAdapter(c, myDataset);
        mRecyclerView.setAdapter(mAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = String.valueOf(spinner1.getSelectedItem());
                ContentValues contentValues = new ContentValues();
                if (myDataset.contains("Rick")) {
                    myDataset.remove("Rick");
                }
                if (!myDataset.contains(location)) {
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

    /*
    public void onClickAddToDb(View view) {

        //TO DO 3.3 Get instances of the edit text widgets and extract their contents
        //TO DO 3.4 Store the contents into a ContentValues Object
        //TO DO 3.5 Insert the ContentValues object into the database
        //TO DO 3.6 (Optional) Display a Toast Message

    }

    public void onClickGetEntireDb(View view) {

        //TO DO 3.7 Call the query or rawQuery method of the spendingDb object and
        //          store the result in a Cursor object

        //TO DO 3.8 Extract the data from the Cursor object and
        //          display it on the textView widget

    }


    public void onClickDeleteFromDb(View view) {


        try {
            //TO DO 3.9 Get an instance of the editText Widget
            //          and extract the contents
            //TO DO 3.10 Delete the entry

        } catch (Exception ex) {
            //TO DO 3.11 Display a toast if an exception occurs
        }

    }

}



*/