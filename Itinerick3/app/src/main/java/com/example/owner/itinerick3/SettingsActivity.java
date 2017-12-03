package com.example.owner.itinerick3;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        initPrefs();

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

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
}
