package com.example.information;

import androidx.appcompat.app.AppCompatActivity;

import android.media.effect.Effect;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.Key;

public class page2 extends AppCompatActivity {

 private ImageView mImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);


        TextView CustomerName = findViewById(R.id.name2);
        TextView CustomerAge = findViewById(R.id.age2);
        mImageview = findViewById(R.id.uploadph2);

        Bundle a =getIntent().getExtras();

        String cn = a.getString("name");
        CustomerName.setText(cn);

        int ca = a.getInt("age");
        CustomerAge.setText(String.valueOf(ca));

        int cph = a.getInt("my_image");
        mImageview.setImageResource(R.drawable.ic_baseline_image_24);







    }


}