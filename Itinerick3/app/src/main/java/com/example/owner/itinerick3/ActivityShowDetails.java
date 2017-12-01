
package com.example.owner.itinerick3;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityShowDetails extends AppCompatActivity {
    private String keyword;
    private String keywordname;
    private ImageView picture;
    private TextView name;
    private TextView description;
    private static final int[] display = {
            R.drawable.buddha,
            R.drawable.gardens,
            R.drawable.hawpar,
            R.drawable.infinity,
            R.drawable.merlion,
            R.drawable.mustafa,
            R.drawable.umbrella,
            R.drawable.safari,
            R.drawable.uss};
    private static final int[] text = {
            R.string.buddha,
            R.string.gardens,
            R.string.hawpar,
            R.string.infinity,
            R.string.merlion,
            R.string.mustafa,
            R.string.umbrella,
            R.string.safari,
            R.string.uss};
    private HashMap<String,String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        data = new HashMap<>();
        data.put("Buddha Tooth Temple","buddha");
        data.put("Gardens by the Bay","gardens");
        data.put("Haw Par Villa","hawpar");
        data.put("Infinity Pool","infinity");
        data.put("Merlion","merlion");
        data.put("Mustafa", "mustafa");
        data.put("Umbrella Trees","umbrella");
        data.put("Night Safari","safari");
        data.put("Universal Studios","uss");
        data.put("Rick","rick");
        picture = (ImageView) findViewById(R.id.detailspic);
        name = (TextView) findViewById(R.id.detailsname);
        description = (TextView) findViewById(R.id.detailsdescription);
        Intent intent = getIntent();
        keywordname = intent.getStringExtra("name");
        keyword = data.get(keywordname);
        String packageName = getPackageName();
        String drawable = "drawable";
        String string = "string";
        int resID1 = getResources().getIdentifier(keyword, drawable, packageName);
        picture.setImageResource(resID1);
        name.setText(keywordname);
        int resID2 = getResources().getIdentifier(keyword, string, packageName);
        description.setText(resID2);
    }

    public void onClickMap (View V){
        String myLocation = keywordname;
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
