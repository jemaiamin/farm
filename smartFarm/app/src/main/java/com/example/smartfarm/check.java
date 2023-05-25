package com.example.smartfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class check extends AppCompatActivity {
    private DatabaseReference waterlevel,humpercent,temppercent;
    private TextView waterlev,humlev,templev;
    private ImageView back;
    private Button actualiser;
    private ProgressBar water_prog_bar,hum_prog_bar,temp_prog_bar,progressbar;
    private int a,b,c;
    private String z,r,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        progressbar=(ProgressBar)findViewById(R.id.Progressbar);
        waterlev=(TextView)findViewById(R.id.Waterprogress_text);
        templev=(TextView)findViewById(R.id.temprogressss_text) ;
        humlev=(TextView)findViewById(R.id.Humprogresss_text);
        water_prog_bar=(ProgressBar)findViewById(R.id.Waterprogress_bar);
        hum_prog_bar=(ProgressBar)findViewById(R.id.Humprogresss_bar);
        temp_prog_bar=(ProgressBar)findViewById(R.id.temprogressss_bar);
        water_prog_bar.setMax(100);
        hum_prog_bar.setMax(100);
        temp_prog_bar.setMax(100);
        waterlevel= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("waterprogress");
        waterlevel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    a = snapshot.getValue(Integer.class);
                    z = String.valueOf(a);
                    water_prog_bar.setProgress(a);
                    waterlev.setText(z + "%");
                }else {
                    progressbar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        humpercent=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("humprogress");
        humpercent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                b=snapshot.getValue(Integer.class);
                r=String.valueOf(b);
                hum_prog_bar.setProgress(b);
                humlev.setText(r + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        temppercent=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("tempprogress");
        temppercent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c=snapshot.getValue(Integer.class);
                t=String.valueOf(c);
                temp_prog_bar.setProgress(c);
                templev.setText(t + "°C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

}}