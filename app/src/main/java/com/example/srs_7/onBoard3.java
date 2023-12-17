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

public class onBoard3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board3);

        RadioButton rd1 = findViewById(R.id.rd1);
        RadioButton rd2 = findViewById(R.id.rd2);
        RadioButton rd3 = findViewById(R.id.rd3);

        rd3.setChecked(true);

        rd2.setClickable(false);
        rd2.setFocusable(false);

        rd1.setClickable(false);
        rd1.setFocusable(false);


        TextView textView = findViewById(R.id.txt_end);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(onBoard3.this, Login.class);
                startActivity(intent);
            }
        });
    }
}

