package dev.caiomartins.androidlab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;


/*
 * Code from https://github.com/arbsantos/Events_Class
 *
 *
 * */

public class PaintCanvas extends View implements View.OnTouchListener {

    private Paint paint = new Paint();
    private Path path = new Path();
    private int backGroundColor = Color.WHITE;
    private GestureDetector mGestureDetector;
    private ArrayList<float[]> points;

    public PaintCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        setBackgroundColor(backGroundColor);
        initPaint();
        this.points = new ArrayList<>();
    }

    public PaintCanvas(Context context, AttributeSet attrs, GestureDetector mGestureDetector) {
        super(context, attrs);
        this.mGestureDetector = mGestureDetector;
        setOnTouchListener(this);
        setBackgroundColor(backGroundColor);
        initPaint();
        this.points = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("CAIO", "CALLED onDraw");
        canvas.drawPath(path, paint);// draws the path with the paint
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false; // let the event go to the rest of the listeners
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("CAIO", "CALLED onTouchEvent");
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("CAIO", "CALLED onTouchEvent DOWN");
                path.moveTo(eventX, eventY);// updates the path initial point

                this.points.add(new float[] {eventX, eventY});
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.i("CAIO", "CALLED onTouchEvent MOVE");
                path.lineTo(eventX, eventY);// makes a line to the point each time this event is fired
                this.points.add(new float[] {eventX, eventY});
                break;
            case MotionEvent.ACTION_UP:// when you lift your finger
                performClick();
                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    public void changeBackground() {
        Random r = new Random();
        backGroundColor = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        setBackgroundColor(backGroundColor);
    }

    public void erase() {
        path.reset();
        this.points = new ArrayList<>();
    }

    private void initPaint() {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setColor(String colorHex) {
        int colorInt = Color.parseColor(colorHex);
        paint.setColor(colorInt);
    }

    public void saveDraw() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://androidlabs-94129-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRefP = database.getReference("points");

        myRefP.setValue(pointsToString());
    }

    public void loadDraw() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://androidlabs-94129-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("points");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = (String) dataSnapshot.getValue();

                erase();
                loadStringToArrayList(value);
                loadPathPointsAsLineTo();
                Log.i("POINTS", value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addPathPoints(float[] points) {
        this.points.add(points);
    }

    public void loadPathPointsAsLineTo() {

        float[] initPoints = this.points.remove(0);
        path.moveTo(initPoints[0], initPoints[1]);
        for (float[] pointSet : this.points) {
            path.lineTo(pointSet[0], pointSet[1]);
        }
    }

    public void loadStringToArrayList(String s) {
        String[] pointSets = s.split(";");

        for (String pointSet : pointSets) {
            String[] set = pointSet.split(",");
            float x = Float.parseFloat(set[0]);
            float y = Float.parseFloat(set[1]);

            this.points.add(new float[] {x, y});
        }
    }

    public String pointsToString() {
        String result = "";
        for (float[] pointsXY : this.points) {
            result += pointsXY[0] + "," + pointsXY[1] + ";";
        }

        return result;
    }

}