
package com.example.owner.itinerick3;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ActivityShowDetails extends AppCompatActivity {
    private String keyword;
    private String mapquery;
    private ImageView picture;
    private TextView name;
    private TextView description;
    private Button button;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initPrefs();
        setContentView(R.layout.activity_show_details);
        picture = findViewById(R.id.detailspic);
        name = findViewById(R.id.detailsname);
        description = findViewById(R.id.detailsdescription);
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
        button = findViewById(R.id.buttonMaps);
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
        boolean checked = sharedPreferences.getBoolean("isBig", false);
        if (checked) {
            button.setTextSize(30);

        } else {
            button.setTextSize(14);
        }
    }

    public void onClickMap (View V){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo").opaquePart("0,0").appendQueryParameter("q",mapquery);
        Uri geoLocation = builder.build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
