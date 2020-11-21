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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    private TextView reg,reset;

    private EditText email, password;
    private Button signin;
    private FirebaseAuth mAuth;
    private String uid;

    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(getApplicationContext());
        reg = (TextView) findViewById(R.id.register);
        reg.setOnClickListener(this);

        reset = (TextView) findViewById(R.id.forgotpassword);
        reset.setOnClickListener(this);

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
            case R.id.forgotpassword:
                startActivity(new Intent(this, ResetPassword.class));
                break;
            case R.id.signin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        final String emailaddress = email.getText().toString().trim();
        String pass = password.getText().toString().trim();


        final DatabaseReference[] reference = {FirebaseDatabase.getInstance().getReference()};
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
                    DatabaseReference reference;
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    uid = user.getUid();


                    if (user.isEmailVerified()) {
                        progressbar.setVisibility(View.GONE);
                        reference.child(uid).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                sessionManager.setLogin(true);
                                User profile = snapshot.getValue(User.class);
                                String role = profile.role.toString();
                                String chmt = "CHMTAdmin";
                                String schmt = "SCHMTAdmin";
                                String linkfacility= "LinkFacilityAdmin";
                                String cho = "CHO";
                                String chv = "CHV";

                                if (role.equals(chmt)) {
                                    startActivity(new Intent(MainActivity.this, CountyAdminActivity.class));
                                }else if(role.equals(schmt)) {
                                    startActivity(new Intent(MainActivity.this, SubCountyAdmin.class));
                                }else if(role.equals(linkfacility)) {
                                    startActivity(new Intent(MainActivity.this, LinkFacility.class));
                                }else if(role.equals(cho)) {
                                    startActivity(new Intent(MainActivity.this, CHO.class));
                                }else if(role.equals(chv)) {
                                    startActivity(new Intent(MainActivity.this, Home.class));
                                }else {
                                    Toast.makeText(MainActivity.this, "You have not been assigned a role in the system. Kindly contact your Administrator.", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }else{
                        user.sendEmailVerification();
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Check your Email to verify account.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Failed to Login. Please check credentials", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                }
            }

            private void checkIfAdmin() {
            }
        });
    }
}