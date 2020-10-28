package com.example.cmamapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private Button reset;
    private FirebaseAuth mAuth;

    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = (EditText) findViewById(R.id.email);

        reset = (Button) findViewById(R.id.buttonreset);
        reset.setOnClickListener(this);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        resetPassword();
    }

    private void resetPassword() {
        String emailaddress = email.getText().toString().trim();

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

        progressbar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(emailaddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "Check your Email.", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(ResetPassword.this, "Try again! Something went wrong.", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
    }
}