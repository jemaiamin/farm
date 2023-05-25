package com.example.smartfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {
    private EditText emaileditText;
    private Button resetPassButton;
    private ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emaileditText=(EditText) findViewById(R.id.reset_pass_email);
        resetPassButton=(Button) findViewById(R.id.Reset_pass_button);
        progressBar=(ProgressBar) findViewById(R.id.reset_pass_Progress);
        auth = FirebaseAuth.getInstance();
        resetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassButton();
            }
        });
    }
    private void resetPassButton(){
        String email = emaileditText.getText().toString().trim();
        if (email.isEmpty()){
            emaileditText.setError("Email is required");
            emaileditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {emaileditText.setError("Please enter a valid Email");
            emaileditText.requestFocus();
            return;}
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(forget_password.this,"check your email to reset password",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(forget_password.this,"Try again !",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}