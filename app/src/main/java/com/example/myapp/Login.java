package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private FirebaseAuth auth;
    ProgressBar progressBar;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myapp-e3efe-default-rtdb.firebaseio.com/");
    private EditText loginEmail,loginPassword;
    private TextView signupRedirectText;
    private Button loginbutton,signupredirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pbar);
        progressBar.setVisibility(View.INVISIBLE);
        signupredirect=findViewById(R.id.signupredirect);
        loginEmail=findViewById(R.id.login_email);
        loginPassword=findViewById(R.id.login_password);
        loginbutton=findViewById(R.id.login_button);
        signupRedirectText=findViewById(R.id.signupRedirectText);
        loginPassword.setTransformationMethod(new PasswordTransformationMethod());
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                if(email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Login.this,"Please Enter Your Email Or Password",Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),studenthomescreen.class);
                                    intent.putExtra("username",email);
                                    intent.putExtra("password",pass);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    signupredirect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent =new Intent(getApplicationContext(),Register.class);
                            startActivity(intent);
                            finish();
                        }
                    });


                }

            }
    });
    }
}
