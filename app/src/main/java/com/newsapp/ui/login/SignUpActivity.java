package com.newsapp.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.newsapp.myapplication.R;


public class SignUpActivity extends AppCompatActivity {
    private  String TAG = "SignUpActivity" ;
    EditText etname,etemail,etpass;
    boolean isEmailValid, isPasswordValid;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();


        etname=findViewById(R.id.editName);
        etemail=findViewById(R.id.editEmail);
        etpass=findViewById(R.id.editPass);

        Button btn_new=findViewById(R.id.buttonAcount);
        final String name=etname.getText().toString().trim();

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etemail.getText().toString().isEmpty()) {
                    etemail.setError(getResources().getString(R.string.email_error));
                    isEmailValid = false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(etemail.getText().toString()).matches()) {
                    etemail.setError(getResources().getString(R.string.error_invalid_email));
                    isEmailValid = false;
                } else  {
                    isEmailValid = true;
                }

                if (etpass.getText().toString().isEmpty()) {
                    etpass.setError(getResources().getString(R.string.password_error));
                    isPasswordValid = false;
                } else if (etpass.getText().length() < 6) {
                    etpass.setError(getResources().getString(R.string.error_invalid_password));
                    isPasswordValid = false;
                } else  {
                    isPasswordValid = true;
                }

                if (isEmailValid && isPasswordValid) {
                    mAuth.createUserWithEmailAndPassword(etemail.getText().toString(), etpass.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(SignUpActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}