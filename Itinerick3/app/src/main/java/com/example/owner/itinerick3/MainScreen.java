package com.example.owner.itinerick3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
    /*Navigate - goes to the Itinerick class where you can search through most of the places, and add them to Itinerrrick!!
    public void onClickNavigate(View v){
        Intent intent = new Intent(this, Map.class);
        EditText editText = (EditText) findViewById(R.id.NavigateText);
        String message = editText.getText().toString();
        intent.putExtra("place", message);
        startActivity(intent);
    }
*/

    //Show Me What You Got - goes to the show me class where a number of Singapore attractions is displayed, can also add them to Itinerick
    public void onClickShow (View V){
        Intent intent = new Intent(this, Location.class);
        startActivity(intent);
    }
}
