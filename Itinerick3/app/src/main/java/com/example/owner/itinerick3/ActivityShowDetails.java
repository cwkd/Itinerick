
package com.example.owner.itinerick3;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityShowDetails extends AppCompatActivity {
    private String keyword;
    private String mapquery;
    private ImageView picture;
    private TextView name;
    private TextView description;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initTheme();
        setContentView(R.layout.activity_show_details);
        picture = (ImageView) findViewById(R.id.detailspic);
        name = (TextView) findViewById(R.id.detailsname);
        description = (TextView) findViewById(R.id.detailsdescription);
        Intent intent = getIntent();
        keyword = intent.getStringExtra(LocationContract.LocationEntry.COL_KEYWORDS);
        mapquery = intent.getStringExtra(LocationContract.LocationEntry.COL_MAP_QUERY);
        String packageName = getPackageName();
        int resID1 = getResources().getIdentifier(keyword, "drawable", packageName);
        picture.setImageResource(resID1);
        name.setText(intent.getStringExtra(LocationContract.LocationEntry.COL_LOCATION));
        name.setTextSize(24);
        description.setText(intent.getStringExtra(LocationContract.LocationEntry.COL_DESCRIPTION));
        description.setTextSize(20);
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

    public void onClickMap (View V){
        String myLocation = mapquery;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo").opaquePart("0,0").appendQueryParameter("q",myLocation);
        Uri geoLocation = builder.build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
