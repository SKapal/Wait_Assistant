package com.example.rickydam.waitassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notif = (Button) findViewById(R.id.not_sender);

        notif.setOnClickListener(v -> {
            System.out.println("NOTIFICATION");
        });

    }
}
