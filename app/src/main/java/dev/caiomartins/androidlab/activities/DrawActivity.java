package dev.caiomartins.androidlab.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import dev.caiomartins.androidlab.GestureListener;
import dev.caiomartins.androidlab.PaintCanvas;
import dev.caiomartins.androidlab.R;
import dev.caiomartins.androidlab.fragments.CanvasFragment;

public class DrawActivity extends AppCompatActivity {

    private CanvasFragment canvasFragment;
    private FragmentManager fragmentManager;
    private EditText customColorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        // Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        fragmentManager = getSupportFragmentManager();
        canvasFragment = (CanvasFragment) fragmentManager.findFragmentById(R.id.canvas_fragment);

        customColorEditText = (EditText) findViewById(R.id.palette_custom_color_et);

    }

    public void erase(View view) {
        canvasFragment.erase();
    }

    public void setCustomColor(View view) {
        String hex = customColorEditText.getText().toString();

        if (hex.isEmpty()) {
            return;
        }

        canvasFragment.setColor(hex);
    }

    public void setColor(View view) {
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        switch (buttonText.toLowerCase()) {
            case "black":
                canvasFragment.setColor("#000000");
                break;
            case "green":
                canvasFragment.setColor("#00FF00");
                break;
            case "red":
                canvasFragment.setColor("#FF0000");
                break;
            case "blue":
                canvasFragment.setColor("#0000FF");
                break;
        }
    }
    public void changeBackgroundColor(View view) {
        canvasFragment.changeBackgroundColor();
    }
}