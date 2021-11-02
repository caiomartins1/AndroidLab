package dev.caiomartins.androidlab.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dev.caiomartins.androidlab.R;

public class SettingsActivity extends AppCompatActivity {

    private EditText colorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        colorEditText = (EditText) findViewById(R.id.settings_et_color);

        // Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void setBackgroundColor(View view) {
        String hex = colorEditText.getText().toString();

        if (hex.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Hex can't be empty!",Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra("colorHex", hex);

        setResult(RESULT_OK, data);
        super.finish();
    }
}