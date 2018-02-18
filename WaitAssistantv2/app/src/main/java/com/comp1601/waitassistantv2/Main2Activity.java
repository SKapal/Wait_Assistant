package com.comp1601.waitassistantv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button register;
    Button viewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        register = findViewById(R.id.activity_main2_register_button);
        viewList = findViewById(R.id.activity_main2_viewList_button);

        register.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent1);
        });

        viewList.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent2);

        });
    }
}
