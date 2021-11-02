package dev.caiomartins.androidlab.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;

import dev.caiomartins.androidlab.GestureListener;
import dev.caiomartins.androidlab.PaintCanvas;
import dev.caiomartins.androidlab.R;

public class DrawActivity extends AppCompatActivity {

    private Fragment canvasFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        // Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }
}