package com.example.srs_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class onBoard1 extends AppCompatActivity {
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board1);
        gestureDetector = new GestureDetector(this, new GestureListener());
        RadioButton rd1 = findViewById(R.id.rd1);
        RadioButton rd2 = findViewById(R.id.rd2);
        RadioButton rd3 = findViewById(R.id.rd3);

        rd1.setChecked(true);

        rd2.setClickable(false);
        rd2.setFocusable(false);

        rd3.setClickable(false);
        rd3.setFocusable(false);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onBoard1.this, Login.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e2.getX() - e1.getX();
            float deltaY = e2.getY() - e1.getY();
            if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (deltaX < 0) {
                    // Свайп влево
                    Toast.makeText(onBoard1.this, "Левый свайп", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(onBoard1.this, onBoard2.class);
                    startActivity(intent);
                } return true;
            } return false;
        }
    }
}