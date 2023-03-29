package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView text_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        text_display = findViewById(R.id.text_display);

        String action = getIntent().getStringExtra("action");

        if (action.equals("BT2")){
            String name = getIntent().getStringExtra("name");
            String phone = getIntent().getStringExtra("phone");
            String gender = getIntent().getStringExtra("gender");
            String education = getIntent().getStringExtra("education");
            int age = getIntent().getIntExtra("age", 1);
            String sport = getIntent().getStringExtra("sport");
            String music = getIntent().getStringExtra("music");
            text_display.setText("Thông tin người dùng" + "\n\n\n\n\n" + "Tên: " + name + "\n\n" + "Số điện thoại: " + phone + "\n\n"
                    + "Giới tính: " + gender + "\n\n" + "Học vấn: " + education + "\n\n" + "Tuổi: " + age + "\n\n"
                    + "Thể thao: "  + sport + "\n\n" + "Thể loại âm nhạc: " + music);
        }

        if (action.equals("btmenu_options")){
            String title = getIntent().getStringExtra("options_item");
            TextView textView = findViewById(R.id.text_display);
            textView.setText(title);
        }

        if (action.equals("btmenu_floating")){
            String title = getIntent().getStringExtra("floating_item");
            TextView textView = findViewById(R.id.text_display);
            textView.setText(title);
        }

        if (action.equals("btmenu_action")){
            String title = getIntent().getStringExtra("action_item");
            TextView textView = findViewById(R.id.text_display);
            textView.setText(title);
        }

        if (action.equals("btmenu_popup")){
            String title = getIntent().getStringExtra("popup_item");
            TextView textView = findViewById(R.id.text_display);
            textView.setText(title);
        }
    }
}