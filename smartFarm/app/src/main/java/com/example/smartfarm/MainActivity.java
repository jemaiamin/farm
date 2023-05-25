package com.example.smartfarm;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView editnewaccount,editForget_Password;
    private EditText editmail,editpassword;
    private Button editSign;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editnewaccount = (TextView) findViewById(R.id.newaccount);
        editnewaccount.setOnClickListener(this);
        editForget_Password = (TextView) findViewById(R.id.forgetpassword);
        editForget_Password.setOnClickListener(this);
        editmail=(EditText) findViewById(R.id.log_inusername);
        editpassword=(EditText) findViewById(R.id.log_inpassword);
        editSign=(Button) findViewById(R.id.button);
        editSign.setOnClickListener(this);
        editForget_Password.findViewById(R.id.forgetpassword);
        editForget_Password.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
        progressbar=(ProgressBar) findViewById(R.id.Progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newaccount:
                startActivity(new Intent(MainActivity.this,sign_in.class));
                break;
            case R.id.button:
                UserLogin();
                break;
            case R.id.forgetpassword:
                startActivity(new Intent(this,forget_password.class));
                break;
        }
    }

    private void UserLogin() {String email=editmail.getText().toString().trim();
        String pass=editpassword.getText().toString().trim();
        if (email.isEmpty()){
            editmail.setError("Email is required");
            editmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {editmail.setError("Please enter a valid Email");
            editmail.requestFocus();
            return;}
        if (pass.isEmpty()){
            editpassword.setError("Password id required");
            editpassword.requestFocus();
            return;}
        if (pass.length()<6) {
            editpassword.setError("Min password length 6 characters");
            editpassword.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                    startActivity(new Intent(MainActivity.this,choose.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "check your email to verify your account", Toast.LENGTH_SHORT).show();
                    }
                }
                else {Toast.makeText(MainActivity.this,"Failed to log in please check your connexion",Toast.LENGTH_LONG).show();
                }
                progressbar.setVisibility(View.GONE);
            }
        });
    }
}