package com.example.owner.itinerick3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BigHead extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bighead);
        ImageView imageGardens = (ImageView) findViewById(R.id.gardens);
        ImageView imageUss = (ImageView) findViewById(R.id.uss);
        ImageView imageSentosa = (ImageView) findViewById(R.id.sentosa);
        ImageView imageMerlion = (ImageView) findViewById(R.id.merlion);
        imageGardens.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BigHead.this,Itinerick3.class);
                startActivity(intent);
            }

        });
        imageUss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BigHead.this,Itinerick3.class);
                startActivity(intent);
            }

        });
        imageSentosa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BigHead.this,Itinerick3.class);
                startActivity(intent);
            }

        });
        imageMerlion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BigHead.this,Itinerick3.class);
                startActivity(intent);
            }

        });
    }





}
