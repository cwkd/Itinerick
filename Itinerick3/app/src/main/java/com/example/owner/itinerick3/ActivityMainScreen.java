package com.example.owner.itinerick3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ActivityMainScreen extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    SharedPreferences sharedPreferences;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        initPrefs();

        setContentView(R.layout.activity_main_screen);
        button = findViewById(R.id.ShowMe);
        textView = findViewById(R.id.intro_text);
        initPrefs2();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("This app shows you a list of suggested places to visit while in Singapore. Get an optimised itinerary thanks to Itinerick!");
                builder.create().show();
        }
        return true;
    }

    public void initPrefs() {
        boolean checked = sharedPreferences.getBoolean("isDark", false);
        if (checked) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        checked = sharedPreferences.getBoolean("isWhacky", false);
        if (checked) {
            setTitle(R.string.alt_app_name);
        } else {
            setTitle(R.string.app_name);
        }
    }

    public void initPrefs2() {
        boolean checked = sharedPreferences.getBoolean("isDark", false);
        if (checked) {
            //button.setBackgroundColor(getResources().getColor(R.color.grey));
        } else {
            //button.setBackgroundColor(getResources().getColor(R.color.pale_grey));
        }
        checked = sharedPreferences.getBoolean("isWhacky", false);
        if (checked) {
            button.setText(R.string.alt_show_me_button);
            textView.setText(R.string.alt_rick_intro);
        } else {
            button.setText(R.string.show_me_button);
            textView.setText(R.string.rick_intro);
        }
    }

    public void changeTheme(boolean isDark) {
        if (isDark) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        recreate();
    }

    public void changeName(boolean isWhacky) {
        if (isWhacky) {
            setTitle(R.string.alt_app_name);
        } else {
            setTitle(R.string.app_name);
        }
        recreate();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        Log.d("PrefChanged", s);
        if (s.equals(getString(R.string.dark_theme_key))) {
            boolean checked = sharedPreferences.getBoolean(s, false);
            changeTheme(checked);
        }

        if (s.equals(getString(R.string.whacky_name_key))) {
            boolean checked = sharedPreferences.getBoolean(s, false);
            changeName(checked);
        }
    }


    //Show Me What You Got - goes to the show me class where a number of Singapore attractions is displayed, can also add them to Itinerick
    public void onClickShow (View V){
        Intent intent = new Intent(this, ActivityLocation.class);
        startActivity(intent);
    }

}
