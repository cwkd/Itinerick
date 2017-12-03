package com.example.owner.itinerick3;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class ActivityLocation extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener, MyAdapter.ViewIdListener {
    private Spinner spinner1;
    private Button btnAdd;
    private Button btnCompute;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;
    private LocationDbHelper locationDbHelper;
    private SQLiteDatabase locationDb;
    DialogFragment dialog;
    int deleteViewId;

    SharedPreferences sharedPreferences;

    public static final String LIST_OF_PLACES = "listOfPlaces";

    LocationJsonData[] locationJsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initTheme();

        setContentView(R.layout.activity_location);

        locationDbHelper = new LocationDbHelper(this);
        locationDb = locationDbHelper.getWritableDatabase();

        LoadDbAsync loadDbAsync = new LoadDbAsync();
        loadDbAsync.execute();
        myDataset = new ArrayList<>();
        myDataset.add("Rick");
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(this, myDataset, this);
        mRecyclerView.setAdapter(mAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = String.valueOf(spinner1.getSelectedItem());
                if (myDataset.contains("Rick")) {
                    myDataset.remove("Rick");
                }
                if (!myDataset.contains(location)) {
                    myDataset.add(location);
                    Log.d("Location ", location);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initTheme() {
        if (getString(R.string.checkDarkThemeKey).equals("true")) {
            boolean checked = sharedPreferences.getBoolean("true", false);
            if (checked) {
                setTheme(R.style.DarkTheme);
            } else {
                setTheme(R.style.AppTheme);
            }
        }
    }

    //Compute Itinerary - goes to the Map Fragment where the shortest path is shown
    public void onClickCompute(View v){
        Intent intent = new Intent(this, FragmentMap.class);
        intent.putExtra(LIST_OF_PLACES, myDataset);
        startActivity(intent);
    }

    public void showDialog() {
        dialog = new DeleteDialogFragment();
        FragmentManager fm = getFragmentManager();
        dialog.show(fm, "DeleteDialog");
    }

    @Override
    public void getViewId(int id) {
        deleteViewId = id;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(this, "Poof! There it's gone...", Toast.LENGTH_LONG).show();
        TextView view = (TextView) findViewById(deleteViewId);
        String string = view.getText().toString();
        myDataset.remove(string);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "We saved your item!", Toast.LENGTH_LONG).show();
    }

    public void showDetails(String name) {
        Intent intent = new Intent(this, ActivityShowDetails.class);
        String query = "SELECT * from "
                + LocationContract.LocationEntry.TABLE_NAME
                + " where "
                + LocationContract.LocationEntry.COL_LOCATION
                + " = '"
                + name
                + "'";
        Cursor cursor = locationDb.rawQuery(query, null);

        intent.putExtra(LocationContract.LocationEntry.COL_LOCATION, name);
        cursor.moveToNext();
        intent.putExtra(LocationContract.LocationEntry.COL_KEYWORDS, cursor.getString(cursor.getColumnIndex(LocationContract.LocationEntry.COL_KEYWORDS)));
        intent.putExtra(LocationContract.LocationEntry.COL_DESCRIPTION, cursor.getString(cursor.getColumnIndex(LocationContract.LocationEntry.COL_DESCRIPTION)));
        intent.putExtra(LocationContract.LocationEntry.COL_MAP_QUERY, cursor.getString(cursor.getColumnIndex(LocationContract.LocationEntry.COL_MAP_QUERY)));
        cursor.close();
        startActivity(intent);
    }

    public class LocationJsonData {
        String name;
        String keyword;
        String description;

        LocationJsonData(String name, String keyword, String description) {
            this.name = name;
            this.keyword = keyword;
            this.description = description;
        }
    }

    private String readTxt(int resource) {
        InputStream inputStream = getResources().openRawResource(resource);
        try {
            return convertInputToString(inputStream, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String convertInputToString(InputStream stream, int len) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    void parseJson() {
        Gson gson = new Gson();
        String locations = readTxt(R.raw.locrec).trim();
        locationJsonData = gson.fromJson(locations, LocationJsonData[].class);
    }

    class LoadDbAsync extends AsyncTask<Void, Void, Void> {

        final String COUNTRY = " Singapore";

        private void addToDB(String name, String keyword, String description) {
            ContentValues cv = new ContentValues();
            cv.put(LocationContract.LocationEntry.COL_LOCATION, name);
            cv.put(LocationContract.LocationEntry.COL_KEYWORDS, keyword);
            cv.put(LocationContract.LocationEntry.COL_DESCRIPTION, description);
            cv.put(LocationContract.LocationEntry.COL_MAP_QUERY, name + COUNTRY);
            locationDb.insert(LocationContract.LocationEntry.TABLE_NAME, null, cv);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            parseJson();
            for (LocationJsonData data: locationJsonData) {
                addToDB(data.name, data.keyword, data.description);
            }
            return null;
        }
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