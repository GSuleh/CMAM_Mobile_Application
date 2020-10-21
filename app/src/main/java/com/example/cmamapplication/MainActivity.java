package com.example.cmamapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView reg;

    private EditText email, password;
    private Button signin;
    private FirebaseAuth mAuth;

    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = (TextView) findViewById(R.id.register);
        reg.setOnClickListener(this);

        signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, register_user.class));
                break;
            case R.id.signin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        final String emailaddress = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (emailaddress.isEmpty()){
            email.setError("Email is required.");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailaddress).matches()){
            email.setError("Please Enter valid email.");
            email.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            password.setError("Password is required.");
            password.requestFocus();
            return;
        }

        if (pass.length() < 6){
            password.setError("Password should be greater than 6 characters.");
            password.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailaddress,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        progressbar.setVisibility(View.GONE);
                        startActivity(new Intent(MainActivity.this, profile.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your Email to verify account.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Failed to Login. Please check credentials", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
    }
}