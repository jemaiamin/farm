package com.example.smartfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class choose extends AppCompatActivity implements View.OnClickListener{
    private ImageView editmanipulate,editcheck,editback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        editcheck=(ImageView) findViewById(R.id.Check_button);
        editcheck.setOnClickListener(this);
        editmanipulate=(ImageView) findViewById(R.id.Manipulate_button);
        editmanipulate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Manipulate_button:
                startActivity(new Intent(this,manipulate.class));
                break;
            case R.id.Check_button:
                startActivity(new Intent(this,check.class));
                break;
    }
}}