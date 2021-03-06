package dev.caiomartins.androidlab.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import dev.caiomartins.androidlab.R;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE = 200;
    private LinearLayoutCompat main_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_ll = findViewById(R.id.main_ll);

        // Retrieving saved background color
        SharedPreferences prefs = getSharedPreferences("bg", MODE_PRIVATE);
        String hex = prefs.getString("color", "#ffffff");
        main_ll.setBackgroundColor(Color.parseColor(hex));

        // Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    public void launchAboutActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void launchSettingsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("colorHex")) {
                String hex = data.getStringExtra("colorHex");


                main_ll.setBackgroundColor(Color.parseColor(hex));

                SharedPreferences.Editor editor = getSharedPreferences("bg", MODE_PRIVATE).edit();
                editor.putString("color", hex);
                editor.apply();
            }
        }
    }

    public void launchDrawActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DrawActivity.class);
        startActivity(intent);
    }
}