package com.example.smartfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class sign_in extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText editfirst_name , editLast_name, editemail ,editpassword , editphone;
    private Button editlog_in;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        editfirst_name=(EditText) findViewById(R.id.first_name);
        editLast_name=(EditText) findViewById(R.id.Last_name);
        editemail=(EditText) findViewById(R.id.Email);
        editphone=(EditText) findViewById(R.id.Phone);
        editpassword=(EditText) findViewById(R.id.Password_sign_in);
        editlog_in=(Button) findViewById(R.id.Sign_in_button);
        editlog_in.setOnClickListener(this);
        progressBar=(ProgressBar)findViewById(R.id.Progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Sign_in_button:
                Sign_inUser();
                break;
        }
    }

    private void Sign_inUser() {
        String First_name=editfirst_name.getText().toString().trim();
        String last_name=editLast_name.getText().toString().trim();
        String email=editemail.getText().toString().trim();
        String Phone=editphone.getText().toString().trim();
        String password=editpassword.getText().toString().trim();
        if (First_name.isEmpty()){
            editfirst_name.setError("first name required");
            editfirst_name.requestFocus();
            return;
        }
        if (last_name.isEmpty()){
            editLast_name.setError("last name required");
            editLast_name.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editemail.setError("email required");
            editemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("Please enter valid email");
            editemail.requestFocus();
            return;
        }

        if (Phone.isEmpty()){
            editphone.setError("phone required");
            editphone.requestFocus();
            return;
        }
        if (Phone.length() != 8 || Phone.matches(Phone) == false){
            editphone.setError("Please provide valid number required");
            editphone.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editpassword.setError("Password required");
            editpassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editpassword.setError("Min password length should be 6 characters");
            editpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            user user = new user(First_name,last_name,email,Phone);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()){
                                        Toast.makeText(sign_in.this,"register has been sign_in successfully!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else {
                                        Toast.makeText(sign_in.this,"TRY AGAIN!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(sign_in.this,"TRY AGAIN!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);}
                    }
                });





    }
}