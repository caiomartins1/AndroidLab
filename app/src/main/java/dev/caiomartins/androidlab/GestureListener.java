package dev.caiomartins.androidlab;

import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.view.GestureDetector;
import android.view.MotionEvent;

/*
* Code from https://github.com/arbsantos/Events_Class
*
*
* */

public class GestureListener extends GestureDetector.SimpleOnGestureListener implements GestureDetector.OnDoubleTapListener {

    private PaintCanvas canvas;
    private SensorManager sensorManager;

    public void setCanvas(PaintCanvas canvas) {
        this.canvas = canvas;
    }

    ////////SimpleOnGestureListener
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        canvas.changeBackground();
    }

    /////////OnDoubleTapListener
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        canvas.erase();
        return false;
    }

}