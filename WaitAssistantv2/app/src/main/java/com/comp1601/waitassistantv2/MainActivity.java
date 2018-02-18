package com.comp1601.waitassistantv2;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView email;
//    private Singleton tmp = Singleton.getInstance();

    TableLayout table;
    private int count = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("List of Patients");

        DatabaseReference mDatabase;


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        table = findViewById(R.id.activity_main_tl_table);
//        table = tmp.getTable();


        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        //retrieveDBElements();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    //String userKey = userSnapshot.getKey();
                    String name = userSnapshot.child("name").getValue(String.class);
                    String email = userSnapshot.child("email").getValue(String.class);
                    String phone = userSnapshot.child("phoneNum").getValue(String.class);
                    addRow(name,email,phone);
                    //Log.d("TEST---------", "Name: "+name+", email: "+email+", phone: "+phone);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
//        String userId = mDatabase.push().getKey();
//        Person p1 = new Person("Sahil","sahilkapal@cmail.carleton.ca", "6136978347");
//
//        mDatabase.child(userId).setValue(p1);

        //email = findViewById(R.id.main_tv_email);

//        email.setOnClickListener(v-> {
//            String str_email = email.getText().toString();
//            sendMail(str_email);
//        });



    }
//
//    public void retrieveDBElements(){
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
//                    //String userKey = userSnapshot.getKey();
//                    String name = userSnapshot.child("name").getValue(String.class);
//                    String email = userSnapshot.child("email").getValue(String.class);
//                    String phone = userSnapshot.child("phoneNum").getValue(String.class);
//                    addRow(name,email,phone);
//                    //Log.d("TEST---------", "Name: "+name+", email: "+email+", phone: "+phone);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


    public void addRow(String name, String email, String phoneNum){

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView nameTextView = new TextView(this);
        nameTextView.setGravity(Gravity.CENTER);

        TextView emailTextView = new TextView(this);
        emailTextView.setGravity(Gravity.CENTER);

        TextView phoneTextView = new TextView(this);
        phoneTextView.setGravity(Gravity.CENTER);

        nameTextView.setText(name);
        emailTextView.setText(email);
        phoneTextView.setText(phoneNum);

        emailTextView.setId(count);

        emailTextView.setOnClickListener(this);

        row.addView(nameTextView);
        row.addView(emailTextView);
        row.addView(phoneTextView);

        row.setGravity(Gravity.CENTER);



        table.addView(row,count++);
//        tmp.setTable(table);

//        table.addView(row,1);
    }



    public void sendMail(String email) {
        Log.i("Send email", "");

        String[] TO = {email};
        String[] CC = {};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Alert");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your appointment is ready");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        int clickedID = v.getId();
        TableRow email = (TableRow) table.getChildAt(clickedID);
        TextView s = (TextView) email.getChildAt(1);
        String st = s.getText().toString();

        sendMail(st);

        //table.getChildAt(1);
        int a = table.indexOfChild(v);
        //TextView t = findViewById(clickedID);
        //String email = t.getText().toString();
        Log.d("GETTING EMAIL---------",""+st);


    }
}
