package com.example.owner.itinerick3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }


    //Show Me What You Got - goes to the show me class where a number of Singapore attractions is displayed, can also add them to Itinerick
    public void onClickShow (View V){
        Intent intent = new Intent(this, ActivityLocation.class);
        startActivity(intent);
    }
}
