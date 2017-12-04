package com.example.owner.itinerick3;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.util.Arrays;

public class ActivityLocation extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogListener, MyAdapter.ViewIdListener {
    private Spinner spinner1;
    private Button btnAdd;
    private Button btnCompute;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;
    private ArrayList<String> mySearchs;
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
        initPrefs();

        setContentView(R.layout.activity_location);

        locationDbHelper = new LocationDbHelper(this);
        locationDb = locationDbHelper.getWritableDatabase();

        LoadDbAsync loadDbAsync = new LoadDbAsync(this);
        loadDbAsync.execute();
        myDataset = new ArrayList<>();
        mySearchs = new ArrayList<>();
        //myDataset.add("Rick");
        spinner1 = findViewById(R.id.spinner1);
        btnAdd = findViewById(R.id.btnAdd);
        btnCompute = findViewById(R.id.btnCompute);
        mRecyclerView = findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = String.valueOf(spinner1.getSelectedItem());
                if (myDataset.contains("Rick")) {
                    myDataset.remove("Rick");
                }
                if (!myDataset.contains(location)) {
                    myDataset.add(location);
                    mySearchs.add(queryForMQ(location));
                    Log.d("Location ", location);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        initPrefs2();
    }

    public void initPrefs() {
        boolean checked = sharedPreferences.getBoolean("isDark", false);
        if (checked) {
            setTheme(R.style.DarkTheme);
            getWindow().getDecorView().setBackgroundResource(R.color.dark_grey);
        } else {
            setTheme(R.style.AppTheme);
            getWindow().getDecorView().setBackgroundResource(R.color.white);
        }
        checked = sharedPreferences.getBoolean("isWhacky", false);
        if (checked) {
            setTitle(R.string.alt_app_name);
        } else {
            setTitle(R.string.app_name);
        }
    }

    public void initPrefs2() {
        boolean checked = sharedPreferences.getBoolean("isWhacky", false);
        if (checked) {
            btnAdd.setText(R.string.alt_add_button);
            btnCompute.setText(R.string.alt_compute_button);
        } else {
            btnAdd.setText(R.string.add_button);
            btnCompute.setText(R.string.compute_button);
        }
        checked = sharedPreferences.getBoolean("isBig", false);
        if (checked) {
            btnAdd.setTextSize(30);
            btnCompute.setTextSize(30);

        } else {
            btnAdd.setTextSize(14);
            btnCompute.setTextSize(14);
        }
    }

    //Compute Itinerary - goes to the Map Fragment where the shortest path is shown
    public void onClickCompute(View v){
        Intent intent = new Intent(this, FragmentMap.class);
        intent.putExtra(LIST_OF_PLACES, mySearchs);
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
        TextView view = findViewById(deleteViewId);
        String string = view.getText().toString();
        myDataset.remove(string);
        mySearchs.remove(queryForMQ(string));
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

    public String queryForLocation(String location) {
        String query = "SELECT * from "
                + LocationContract.LocationEntry.TABLE_NAME
                + " where "
                + LocationContract.LocationEntry.COL_LOCATION
                + " = '"
                + location
                + "'";
        Cursor cursor = locationDb.rawQuery(query, null);
        cursor.moveToNext();
        String keyword = cursor.getString(cursor.getColumnIndex(LocationContract.LocationEntry.COL_KEYWORDS));
        cursor.close();
        return keyword;
    }

    public String queryForMQ(String location) {
        String query = "SELECT * from "
                + LocationContract.LocationEntry.TABLE_NAME
                + " where "
                + LocationContract.LocationEntry.COL_LOCATION
                + " = '"
                + location
                + "'";
        Cursor cursor = locationDb.rawQuery(query, null);
        cursor.moveToNext();
        String keyword = cursor.getString(cursor.getColumnIndex(LocationContract.LocationEntry.COL_MAP_QUERY));
        cursor.close();
        return keyword;
    }

    public class LocationJsonData {
        String name;
        String keyword;
        String description;
        String mapquery;

        LocationJsonData(String name, String keyword, String description, String mapquery) {
            this.name = name;
            this.keyword = keyword;
            this.description = description;
            this.mapquery = mapquery;
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

    class ComputeAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    class LoadDbAsync extends AsyncTask<Void, Void, Void> {

        final String COUNTRY = " Singapore";
        String[] locations;
        int count;
        Context parentContext;
        ActivityLocation activityLocation;

        LoadDbAsync(Context context) {
            parentContext = context;
            activityLocation = (ActivityLocation) context;
        }

        private void addToDB(String name, String keyword, String description, String mapquery) {
            ContentValues cv = new ContentValues();
            cv.put(LocationContract.LocationEntry.COL_LOCATION, name);
            cv.put(LocationContract.LocationEntry.COL_KEYWORDS, keyword);
            cv.put(LocationContract.LocationEntry.COL_DESCRIPTION, description);
            cv.put(LocationContract.LocationEntry.COL_MAP_QUERY, mapquery + COUNTRY);
            locationDb.insert(LocationContract.LocationEntry.TABLE_NAME, null, cv);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            parseJson();
            count = 0;
            locations = new String[locationJsonData.length];
            for (LocationJsonData data: locationJsonData) {
                addToDB(data.name, data.keyword, data.description, data.mapquery);
                locations[count] = data.name;
                count++;
            }
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(parentContext, R.layout.spinner_item, new ArrayList<>(Arrays.asList(locations)));
            try {
                spinner1.setAdapter(spinnerAdapter);
                mAdapter = new MyAdapter(parentContext, myDataset, activityLocation);
                mRecyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            myDataset.add("Marina Bay Sands");
            mySearchs.add(queryForMQ("Marina Bay Sands"));
            return null;
        }
    }
}