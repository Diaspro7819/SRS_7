package com.example.srs_7;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class Login extends AppCompatActivity {
    private EditText editTextEmail;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignIn.setEnabled(false); // Initially disable the button
        // Add a text watcher to enable/disable the button based on email input
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    // If the entered email matches the predefined pattern, enable the button
                    btnSignIn.setEnabled(true);
                } else {
                    // Otherwise, disable the button
                    btnSignIn.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Messange.class);
                startActivity(intent);
            }
        });
        Button btnYandex = findViewById(R.id.btn_yandex);
        btnYandex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/id/about"));
                startActivity(intent);            }
        });
    }
}