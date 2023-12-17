package com.example.srs_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
public class Messange extends AppCompatActivity {
    private static final long COUNTDOWN_TIME = 60000; // 1 минута
    private static final int MAX_ATTEMPTS = 3;
    private CountDownTimer countDownTimer;
    private boolean codeEntered = false;
    private int generatedCode;
    private int attemptsLeft = MAX_ATTEMPTS;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messange);

        Button sendCodeButton = findViewById(R.id.sendCodeButton);
        TextView timerTextView = findViewById(R.id.timerTextView);
        EditText userInput = findViewById(R.id.etNumber);

        sendCodeButton.setOnClickListener(view -> {
            if (!isTimerRunning) {
                if (attemptsLeft > 0) {
                    generateAndSendCode();
                    startCountdown(timerTextView);
                    attemptsLeft--;
                    isTimerRunning = true;
                    sendCodeButton.setEnabled(false); // Блокировка кнопки
                } else {
                    Toast.makeText(this, "Вы использовали все попытки, подождите 1 минуту", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Подождите завершения таймера", Toast.LENGTH_SHORT).show();
            }
        });

        userInput.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String inputCode = userInput.getText().toString();
                int enteredCode = Integer.parseInt(inputCode);

                if (enteredCode == generatedCode) {
                    codeEntered = true;
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                    startNextActivity();
                    return true;
                } else {
                    Toast.makeText(this, "Неправильный код", Toast.LENGTH_SHORT).show();
                    userInput.getText().clear();
                }
            }
            return false;
        });
    }

    private void generateAndSendCode() {
        Random random = new Random();
        generatedCode = random.nextInt(10000);
        showNotification(generatedCode);
    }

    private void showNotification(int generatedCode) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.image_1)
                .setContentTitle("Код для ввода")
                .setContentText(String.valueOf(generatedCode))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    private void startCountdown(TextView timerTextView) {
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Оставшееся время: " + millisUntilFinished / 1000 + " сек");
            }

            public void onFinish() {
                isTimerRunning = false;
                Toast.makeText(Messange.this, "Истекло время ввода кода, запросите новый", Toast.LENGTH_SHORT).show();
                Button sendCodeButton = findViewById(R.id.sendCodeButton);
                sendCodeButton.setEnabled(true); // Разблокировка кнопки после истечения времени
            }
        }.start();
    }

    private void startNextActivity() {
        Intent intent = new Intent(Messange.this, Password.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Запуск таймера после возврата на активность
        if (countDownTimer != null) {
            countDownTimer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Остановка таймера при уходе с активности
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
