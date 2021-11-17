package dev.caiomartins.androidlab.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.caiomartins.androidlab.GestureListener;
import dev.caiomartins.androidlab.PaintCanvas;
import dev.caiomartins.androidlab.R;


public class CanvasFragment extends Fragment {

    private GestureListener mGestureListener;
    private GestureDetector mGestureDetector;
    private PaintCanvas paintCanvas;

    public CanvasFragment() {
        // Required empty public constructor
    }


    public static CanvasFragment newInstance(String param1, String param2) {
        CanvasFragment fragment = new CanvasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_canvas, container, false);

        mGestureListener = new GestureListener();
        mGestureDetector = new GestureDetector(getActivity().getApplicationContext(), mGestureListener);
        mGestureDetector.setIsLongpressEnabled(true);
        mGestureDetector.setOnDoubleTapListener(mGestureListener);

        paintCanvas = new PaintCanvas(getActivity().getApplicationContext(), null, mGestureDetector);
        mGestureListener.setCanvas(paintCanvas);

        RelativeLayout rl = v.findViewById(R.id.draw_canvas_rl);
        rl.addView(paintCanvas);

        return v;
    }

    public void erase() {
        paintCanvas.erase();
    }

    public void setColor(String colorHex) {
        paintCanvas.setColor(colorHex);
    }

    public void changeBackgroundColor() {
        paintCanvas.changeBackground();
    }

    public void saveDraw() {
        paintCanvas.saveDraw();
    }

    public void loadDraw() { paintCanvas.loadDraw(); }
}