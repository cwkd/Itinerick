
package com.example.owner.itinerick3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityShowDetails extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        picture = (ImageView) findViewById(R.id.detailspic);
        name = (TextView) findViewById(R.id.detailsname);
        description = (TextView) findViewById(R.id.detailsdescription);
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("name");
        String packageName = getPackageName();
        String drawable = "drawable";
        String string = "string";
        int resID1 = getResources().getIdentifier(keyword, drawable, packageName);
        picture.setImageResource(resID1);
        name.setText(keyword);
        int resID2 = getResources().getIdentifier(keyword, string, packageName);
        description.setText(resID2);
    }


}
