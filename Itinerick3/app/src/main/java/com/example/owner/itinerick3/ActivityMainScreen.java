package com.example.owner.itinerick3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ActivityMainScreen extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        initTheme();

        setContentView(R.layout.activity_main_screen);
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

    public void changeTheme(boolean isDark) {
        if (isDark) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        recreate();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        if (s.equals(getString(R.string.checkDarkThemeKey))) {
            boolean checked = sharedPreferences.getBoolean(s, false);
            changeTheme(checked);
        }
    }


    //Show Me What You Got - goes to the show me class where a number of Singapore attractions is displayed, can also add them to Itinerick
    public void onClickShow (View V){
        Intent intent = new Intent(this, ActivityLocation.class);
        startActivity(intent);
    }
}
