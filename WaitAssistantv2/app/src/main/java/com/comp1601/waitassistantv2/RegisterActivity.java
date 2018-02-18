package com.comp1601.waitassistantv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private Singleton tmp = Singleton.getInstance();
    private TableLayout table;


    EditText name;
    EditText email;
    EditText phone;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DatabaseReference mDatabase;

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        name = findViewById(R.id.activity_register_name_editText);
        email = findViewById(R.id.activity_register_email_editText);
        phone = findViewById(R.id.activity_register_phone_editText);
        submit = findViewById(R.id.activity_register_submit_button);

        table = findViewById(R.id.activity_main_tl_table);
        table = tmp.getTable();

        submit.setOnClickListener(v -> {


            String fname = name.getText().toString();
            String uEmail = email.getText().toString();
            String uPhone = phone.getText().toString();

            if (validator(fname,uEmail,uPhone)){
                Person person = new Person(fname,uEmail,uPhone);
                String userId = mDatabase.push().getKey();
                mDatabase.child(userId).setValue(person);
//                addRow(fname,uEmail,uPhone);
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }

        });




    }

    public boolean validator(String name, String email, String phoneNum){
        if (name.trim().equals("") || email.trim().equals("") || phoneNum.trim().equals("")) {
            Toast.makeText(this, "Missing fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    public void addRow(String name, String email, String phoneNum){
//
//        TableRow row = new TableRow(this);
//        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//        row.setLayoutParams(lp);
//
//        TextView nameTextView = new TextView(this);
//        TextView emailTextView= new TextView(this);
//        TextView phoneTextview = new TextView(this);
//
//        nameTextView.setText(name);
//        emailTextView.setText(email);
//        phoneTextview.setText(phoneNum);
//
//        row.addView(nameTextView);
//        row.addView(emailTextView);
//        row.addView(phoneTextview);
//
//        table.addView(row,1);
//        tmp.setTable(table);
//
//    }


}
