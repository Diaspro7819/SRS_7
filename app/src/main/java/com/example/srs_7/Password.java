package com.example.srs_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Password extends AppCompatActivity {
    private RadioButton[] radioButtons = new RadioButton[4];

    private String enteredPin = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        TextView textView = findViewById(R.id.textView13);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Password.this, MedCard.class);
                startActivity(intent);
            }
        });

        radioButtons[0] = findViewById(R.id.rd1);
        radioButtons[1] = findViewById(R.id.rd2);
        radioButtons[2] = findViewById(R.id.rd3);
        radioButtons[3] = findViewById(R.id.rd4);

        LinearLayout pinDisplay = findViewById(R.id.linearLayout);
        ImageView btnDelete = findViewById(R.id.delete);

        Button[] numberButtons = new Button[]{
                findViewById(R.id.btn0),
                findViewById(R.id.btn1),
                findViewById(R.id.btn2),
                findViewById(R.id.btn3),
                findViewById(R.id.btn4),
                findViewById(R.id.btn5),
                findViewById(R.id.btn6),
                findViewById(R.id.btn7),
                findViewById(R.id.btn8),
                findViewById(R.id.btn9)
        };
        for (final Button button : numberButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enteredPin.length() < 4) {
                        enteredPin += button.getText().toString();
                        updatePinDisplay(pinDisplay, enteredPin);
                    }
                }
            });
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enteredPin.length() > 0) {
                    enteredPin = enteredPin.substring(0, enteredPin.length() - 1);
                    updatePinDisplay(pinDisplay, enteredPin);
                }
            }
        });
    }
    private void updatePinDisplay(LinearLayout pinDisplay, String enteredPin) {
        int length = enteredPin.length();
        for (int i = 0; i < 4; i++) {
            if (i < length) {
                radioButtons[i].setChecked(true);
            } else {
                radioButtons[i].setChecked(false);
            }
        }
        if (length == 4) {
            showToast("Успешно сохранено. Код: " + enteredPin);
            navigateToNewActivity(); // Вызов метода для перехода на новую активность
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void navigateToNewActivity() {
        Intent intent = new Intent(this, MedCard.class); // NewActivity - ваша новая активность
        startActivity(intent);
    }
}