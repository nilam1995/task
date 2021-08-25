package com.CleverTapTest.clevertapandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton button, pushInfo;
    private EditText name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_productViewed);
        pushInfo = findViewById(R.id.btn_pushInfo);
        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
                prodViewedAction.put("Product Id", "1");
                prodViewedAction.put("Product Image", "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg");
                prodViewedAction.put("Product Name", "CleverTap");

                clevertapDefaultInstance.pushEvent("Product Viewed", prodViewedAction);
            }
        });

        pushInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(email.getText().toString())){
                    HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                    profileUpdate.put("Name", name.getText().toString());
                    profileUpdate.put("Email", email.getText().toString());

                    clevertapDefaultInstance.pushProfile(profileUpdate);
                }else {
                    Toast.makeText(MainActivity.this,"Please enter name and email", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}