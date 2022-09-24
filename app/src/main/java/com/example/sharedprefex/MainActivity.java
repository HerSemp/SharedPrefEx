package com.example.sharedprefex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button bt_save,bt_display,bt_delete;
    public EditText ed_name,ed_password;
    public TextView textView,textView2;
    public final String SharedDatabase = "myDatabase";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_delete = findViewById(R.id.bt_delete);
        bt_save = findViewById(R.id.bt_save);
        bt_display = findViewById(R.id.bt_display);
        ed_name = findViewById(R.id.ed_name);
        ed_password = findViewById(R.id.ed_password);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textVie2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = ed_name.getText().toString();
                String passw = ed_password.getText().toString();

                SharedPreferences.Editor editor =  getSharedPreferences(SharedDatabase,MODE_PRIVATE).edit();
                editor.putString("username",user);
                editor.putString("password",passw);
                editor.apply();
            }
        });
        bt_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(SharedDatabase,MODE_PRIVATE);
                String user = preferences.getString("username","Error: field empty");
                String passw = preferences.getString("password","Error: field empty");
                textView2.setText(user);
                textView.setText(passw);



            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(SharedDatabase,MODE_PRIVATE);
                preferences.edit().clear().commit();
                preferences.edit().apply();

            }
        });
    }
}