package com.example.mytestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneLoginActivity extends AppCompatActivity {

    EditText phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        phonenumber=findViewById(R.id.PhoneNumText);

        findViewById(R.id.verifybutton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String mobile=phonenumber.getText().toString().trim();

                if(mobile.isEmpty()||mobile.length()<10){
                    phonenumber.setError("Enter a valid number ");
                    phonenumber.requestFocus();
                    return;
                }

                Intent intent=new Intent(PhoneLoginActivity.this,VerificationActivity.class);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
            }

        });
    }
}
