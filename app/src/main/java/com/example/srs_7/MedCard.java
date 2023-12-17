package com.example.srs_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MedCard extends AppCompatActivity {
    Button createButton;
    EditText editTextName;
    EditText editTextPatronymic;
    EditText editTextSurname;
    EditText editTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_card);

        createButton = findViewById(R.id.button4);
        editTextName = findViewById(R.id.editTextText);
        editTextPatronymic = findViewById(R.id.editTextText2);
        editTextSurname = findViewById(R.id.editTextText3);
        editTextDate = findViewById(R.id.editTextDate);

        Spinner spinnerGender = findViewById(R.id.spinnerGender);
        String[] genders = {"Мужской", "Женский"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Проверяем, все ли поля заполнены
                boolean allFieldsFilled = !editTextName.getText().toString().isEmpty() &&
                        !editTextPatronymic.getText().toString().isEmpty() &&
                        !editTextSurname.getText().toString().isEmpty() &&
                        !editTextDate.getText().toString().isEmpty();
                // Если все поля заполнены, разблокировать кнопку, иначе заблокировать
                createButton.setEnabled(allFieldsFilled);
            }
        };
        // Добавляем слушатели событий для полей ввода
        editTextName.addTextChangedListener(textWatcher);
        editTextPatronymic.addTextChangedListener(textWatcher);
        editTextSurname.addTextChangedListener(textWatcher);
        editTextDate.addTextChangedListener(textWatcher);
        // Изначально заблокировать кнопку
        createButton.setEnabled(false);
    }
}