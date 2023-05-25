package com.example.smartfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class manipulate extends AppCompatActivity {
    private SwitchCompat SwitchVale,SwitchPump,SwitchFan;
    private boolean SwitchValestate,SwitchPumpstate,SwitchFanstate;
    private DatabaseReference ValeData,PumpData,FanData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulate);
        SwitchFan=findViewById(R.id.Fan_button);
        SwitchFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SwitchFanstate=true;
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchFanstate").setValue(SwitchFanstate);
                    Toast.makeText(getApplicationContext(),"Fan ON",Toast.LENGTH_LONG).show();
                }else {
                    SwitchFanstate=false;
                    Toast.makeText(getApplicationContext(),"Fan OFF",Toast.LENGTH_LONG).show();
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchFanstate").setValue(SwitchFanstate);
                }
            }
        });
        SwitchPump=findViewById(R.id.Pump_button);
        user user =new user(SwitchValestate,SwitchPumpstate,SwitchFanstate);
        SwitchPump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SwitchPumpstate=true;
                    Toast.makeText(getApplicationContext(),"Pump ON",Toast.LENGTH_LONG).show();

                }else {
                    SwitchPumpstate=false;
                    Toast.makeText(getApplicationContext(),"Pump OFF",Toast.LENGTH_LONG).show();
                }
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchPumpstate").setValue(SwitchPumpstate);
            }
        });
        SwitchVale =findViewById(R.id.vale_button);
        SwitchVale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SwitchValestate=true;
                    Toast.makeText(getApplicationContext(),"Vale ON",Toast.LENGTH_LONG).show();
                }else {
                    SwitchValestate=false;
                    Toast.makeText(getApplicationContext(),"Vale OFF",Toast.LENGTH_LONG).show();
                }
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchValestate").setValue(SwitchValestate);
            }
        });
        ValeData =FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchValestate");
        ValeData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().equals(true)){
                    SwitchVale.setChecked(true);
                }else {
                    SwitchVale.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FanData =FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchFanstate");
        FanData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().equals(true)){
                    SwitchFan.setChecked(true);
                }else {
                    SwitchFan.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        PumpData =FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("SwitchPumpstate");
        PumpData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().equals(true)){
                    SwitchPump.setChecked(true);
                }else {
                    SwitchPump.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}