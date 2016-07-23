package com.faravy.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView nameShow;
    TextView phoneShow;
    TextView idShow;
    Button updateB;
    Button deleteB;
    int id;
    String name;
    String phone;
    ContactManager manager;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameShow = (TextView) findViewById(R.id.txtNameIn);
        idShow = (TextView) findViewById(R.id.txtIdIn);
        phoneShow = (TextView) findViewById(R.id.txtPhoneIn);
        updateB=(Button)findViewById(R.id.btnUpdate);
        deleteB=(Button)findViewById(R.id.btnDelete);

        manager = new ContactManager(this);

        Intent intent=getIntent();
        id = intent.getIntExtra("id",-1);
        contact=manager.getContact(id);

        nameShow.setText(contact.getName());
        phoneShow.setText(contact.getPhoneNo());
        idShow.setText(contact.getId()+"");
        //Toast.makeText(SecondActivity.this, id+ ": id", Toast.LENGTH_SHORT).show();

    }

    public void delete(View view) {
       // int in=Integer.parseInt(id);
        boolean delete =manager.deleteContact(id);
        if (delete) {
            Toast.makeText(SecondActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SecondActivity.this, "Record has not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
        Intent intentS=new Intent(SecondActivity.this,MainActivity.class);
        intentS.putExtra("idS",id);
        startActivity(intentS);
    }
}
