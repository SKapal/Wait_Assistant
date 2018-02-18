package com.comp1601.waitassistantv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    EditText name;
    EditText email;
    EditText phone;

    Button submit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        name = findViewById(R.id.activity_register_name_editText);
        email = findViewById(R.id.activity_register_email_editText);
        phone = findViewById(R.id.activity_register_phone_editText);
        submit = findViewById(R.id.activity_register_submit_button);

        submit.setOnClickListener(v -> {
            String fname = name.getText().toString();
            String uEmail = email.getText().toString();
            String uPhone = phone.getText().toString();

            Person person = new Person(fname,uEmail,uPhone);

            String userId = mDatabase.push().getKey();
            mDatabase.child(userId).setValue(person);
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        });




    }
}
