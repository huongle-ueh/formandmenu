package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class BT2 extends AppCompatActivity{

    String[] arraySpinner;
    TextView text_progress;
    EditText input_name, input_phone;
    Switch switch_gender;
    Spinner spinner_education;
    SeekBar seekbar_age;
    CheckBox checkbox_sport;
    RadioGroup radio_group;
    RadioButton radio_choice;
    Button button_register, button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt2);

        Intent intent = new Intent(BT2.this, DisplayActivity.class);
        intent.putExtra("gender", "Nam");
        intent.putExtra("age", 1);
        intent.putExtra("sport", "Không chơi thể thao");
        intent.putExtra("music", "Không có sở thích");
        input_name = findViewById(R.id.input_name);
        input_phone = findViewById(R.id.input_phone);
        switch_gender = findViewById(R.id.switch_gender);
        switch_gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_gender.isChecked()){
                    intent.putExtra("gender", "Nữ");
                }else{
                    intent.putExtra("gender", "Nam");
                }
            }
        });

        arraySpinner = new String[] {"Cao đẳng", "Đại học", "Cao học"};
        spinner_education = findViewById(R.id.spinner_education);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_education.setAdapter(adapter);
        spinner_education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                intent.putExtra("education", selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                intent.putExtra("education", "Not selected");
            }
        });


        seekbar_age = findViewById(R.id.seekbar_age);
        text_progress = findViewById(R.id.text_progress);
        seekbar_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_progress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                intent.putExtra("age", seekBar.getProgress());
            }
        });



        checkbox_sport = findViewById(R.id.checkbox_sport);
        checkbox_sport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkbox_sport.isChecked() == true){
                    intent.putExtra("sport", "Có chơi thể thao");
                }
                else {
                    intent.putExtra("sport", "Không chơi thể thao");
                }
            }
        });

        radio_group = findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadio = radio_group.getCheckedRadioButtonId();
                radio_choice = findViewById(checkedRadio);
                if (checkedRadio == -1){
                    intent.putExtra("music", "Không có sở thích");
                }else {
                    intent.putExtra("music", radio_choice.getText().toString());
                }
            }
        });

        button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_name.getText().toString().equals("")){
                    intent.putExtra("name", "Huong Le");
                }else {
                    intent.putExtra("name", input_name.getText().toString());
                }

                if (input_phone.getText().toString().equals("")){
                    intent.putExtra("phone", "0909090909");
                }else {
                    intent.putExtra("phone", input_phone.getText().toString());
                }

                intent.putExtra("action", "BT2");

                startActivity(intent);
            }
        });

        button_cancel = findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_name.setText("");
                input_phone.setText("");
                switch_gender.setChecked(false);
                spinner_education.setSelection(0);
                seekbar_age.setProgress(1);
                checkbox_sport.setChecked(false);
                radio_group.clearCheck();
            }
        });
    }
}