package com.example.rickydam.waitassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.amazonaws.mobile.client.AWSMobileClient;

public class MainActivity extends AppCompatActivity {

    private Button notif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AWSMobileClient.getInstance().initialize(this).execute();

        notif = (Button) findViewById(R.id.not_sender);

        notif.setOnClickListener(v -> {
            System.out.println("NOTIFICATION");
        });

    }
}
