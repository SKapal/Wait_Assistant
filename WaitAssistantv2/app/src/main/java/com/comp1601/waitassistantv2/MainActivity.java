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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView email;
//    private Singleton tmp = Singleton.getInstance();

    TableLayout table;
    private int count = 1;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("List of Patients");




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



    }



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
        phoneTextView.setId(count);

        emailTextView.setOnClickListener(this);
        phoneTextView.setOnClickListener(this);

        row.addView(nameTextView);
        row.addView(emailTextView);
        row.addView(phoneTextView);

        row.setGravity(Gravity.CENTER);
        row.setPadding(0,40,0,40);
        row.setBackground(getResources().getDrawable(R.drawable.shape));



        table.addView(row,count++);
//        tmp.setTable(table);

//        table.addView(row,1);
    }

    public void sendText(String phone, int clickId){
        String message = "Your appointment is ready";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
        intent.putExtra("sms_body", message);
        startActivity(intent);

        removeRow(clickId);
    }

    public void sendMail(String email, int clickId) {
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
            removeRow(clickId);
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onClick(View v) {
        int clickedID = v.getId();
        String phoneString = "";
        String emailString = "";

        if(v.getLeft() > 750){
            TableRow phone = (TableRow) table.getChildAt(clickedID);
            TextView phoneTextView = (TextView) phone.getChildAt(2);
            phoneString = phoneTextView.getText().toString();


        }else{

            TableRow email = (TableRow) table.getChildAt(clickedID);
            TextView emailTextview = (TextView) email.getChildAt(1);
            emailString = emailTextview.getText().toString();


        }


        Log.d("Before if----------","-------------");
        Log.d("GETTING EMAIL---------",""+emailString);
        Log.d("GETTING PHONE---------",""+phoneString);
        if(!emailString.equals("")){ sendMail(emailString, clickedID); }
        else{
            sendText(phoneString,clickedID);
        }



        //table.getChildAt(1);
        int a = table.indexOfChild(v);
        //TextView t = findViewById(clickedID);
        //String email = t.getText().toString();
        Log.d("GETTING EMAIL---------",""+phoneString);

        emailString = "";
        phoneString = "";


    }

    public void removeRow(int id){

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            int count = 1;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    if(id == count){
                        String key = userSnapshot.getKey();
                        Log.d("REMOVAL KEy------", key);
                        mDatabase.child(key).removeValue();

                    }
                    count++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
