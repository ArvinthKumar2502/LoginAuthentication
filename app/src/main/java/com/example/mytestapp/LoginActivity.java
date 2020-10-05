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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //EditText emailid;
    EditText password;
    EditText phonenum;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth=FirebaseAuth.getInstance();
        //emailid=findViewById(R.id.EmailIdText);
        phonenum=findViewById(R.id.PhoneText);
        password=findViewById(R.id.PasswordText);
        btnSignIn=findViewById(R.id.SignUpButton);
        tvSignUp=findViewById(R.id.MaintextView);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFireBaseUser=mFirebaseAuth.getCurrentUser();

            if(mFireBaseUser != null){
                Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();

            }

            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email=emailid.getText().toString();
                String phonenumber=phonenum.getText().toString();
                String pwd=password.getText().toString();
                if(phonenumber.isEmpty() || pwd.isEmpty()) {
                    //emailid.setError("Please enter an email id");
                    Toast.makeText(LoginActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();

                    phonenum.requestFocus();
                }

                else if(!(phonenumber.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(phonenumber,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error,Please Login Again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Intent inToHome=new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Error Occured!!",Toast.LENGTH_SHORT).show();

                }

            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intSignUp);

            }
        });
    }
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
