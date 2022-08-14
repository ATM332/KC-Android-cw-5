package com.example.information;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView mImageview;
    Button mChoosebtn;

    private static final int image_pick_code = 1000;
    private static final int permission_code = 1001;
    private EditText name;
    private EditText age;
    private ImageButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //تبع الupload image
        mImageview = findViewById(R.id.uploadph);
        mChoosebtn = findViewById(R.id.changeimage);


        //تبع الupload image
        mChoosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //pemision not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, permission_code);

                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }

                }
            }
        });

        TextView app = findViewById(R.id.app1);
        EditText name = findViewById(R.id.name1);
        EditText age = findViewById(R.id.age1);
        ImageButton button = findViewById(R.id.bttn);
        ImageView photo = findViewById(R.id.uploadph);

        button.setEnabled(false);

        name.addTextChangedListener(textWatcher);
        age.addTextChangedListener(textWatcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = app.getText().toString();
                String b = name.getText().toString();
                int c = Integer.parseInt(age.getText().toString());


                Intent next = new Intent(MainActivity.this, page2.class);

                next.putExtra("appname", a);
                next.putExtra("name", b);
                next.putExtra("age", c);
                next.putExtra("my_image",R.drawable.ic_baseline_image_24);

                startActivity(next);

            }
        });


    }















    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            name = findViewById(R.id.name1);
            age = findViewById(R.id.age1);
            button = findViewById(R.id.bttn);

            String nameInput = name.getText().toString();
            String ageInput = age.getText().toString();



            if(!nameInput.isEmpty() && !ageInput.isEmpty()){
                button.setEnabled(true);
            }else{
                button.setEnabled(false);


            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }





        };








        // هذا كله تبع الupload حق الimage}
    private void  pickImageFromGallery(){
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, image_pick_code);
    }


    // هذا كله تبع الupload حق الimage
    //handle result runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case permission_code: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();

                } else {
                    //permission was denied
                    Toast.makeText(this, "Pemission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //handle result for pick image

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == image_pick_code) {
            //set image to image view
            mImageview.setImageURI(data.getData());
        }
    }
}
