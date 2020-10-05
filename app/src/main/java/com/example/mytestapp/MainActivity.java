package com.example.mytestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailid,password;
    EditText name,phone;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.EmailIdText);
        password=findViewById(R.id.PasswordText);
        btnSignUp=findViewById(R.id.SignUpButton);
        tvSignIn=findViewById(R.id.MaintextView);
        name=findViewById(R.id.NameText);
        phone=findViewById(R.id.PhoneText);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=name.getText().toString();
                String phonenum=phone.getText().toString();
                String email=emailid.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty() || pwd.isEmpty()||uname.isEmpty()||phonenum.isEmpty()) {
                    //emailid.setError("Please enter an email id");
                    Toast.makeText(MainActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();

                    emailid.requestFocus();
                }

                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"SignUp Unsuccessful,Please Try Again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occured!!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,PhoneLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
