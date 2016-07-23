package com.faravy.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nameET;
    EditText phoneNoET;
    Contact contact;
    Contact contactFetch;
    ContactManager manager;
    ListView lstContact;
    ArrayList<Contact> arrListAll;
    CustomAdapter adapter;
    int idS;
    String nameS;
    String phoneS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = (EditText) findViewById(R.id.nameET);
        phoneNoET = (EditText) findViewById(R.id.phoneNoET);
        lstContact=(ListView)findViewById(R.id.lstData);
        manager = new ContactManager(this);

        Intent intentS=getIntent();
        idS = intentS.getIntExtra("idS",-1);
        if(idS>0){
            contactFetch=manager.getContact(idS);
            nameET.setText(contactFetch.getName());
            phoneNoET.setText(contactFetch.getPhoneNo());
        }
        listData();

    }

    public void saveToDatabase(View view) {

        if(idS>0)
        {updateRecord();
        }
        else {
            String name = nameET.getText().toString();
            String phoneNo = phoneNoET.getText().toString();

            contact = new Contact(name, phoneNo);

            boolean inserted = manager.addContact(contact);
            if (inserted) {
                Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        }

      /*  ArrayList<Contact> contacts=manager.getAllContacts();
        String name=contacts.get(0).getName();*/
        //Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

       /* Contact contact=manager.getContact(1);
        Toast.makeText(MainActivity.this, contact.getName(), Toast.LENGTH_SHORT).show();
*/

     /*   Contact contact=new Contact("Fahim","1234");
        manager.updateContact(1,contact);

        Contact updatedContact=manager.getContact(1);
        Toast.makeText(MainActivity.this, updatedContact.getName(), Toast.LENGTH_SHORT).show();*/
        listData();
    }

    public void listData(){
        ////arrListAll=new ArrayList<Contact>();
        arrListAll=manager.getAllContacts();
        adapter=new CustomAdapter(MainActivity.this, arrListAll);
        lstContact.setAdapter(adapter);

        lstContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("id",arrListAll.get(i).getId());
                startActivity(intent);
            }
        });
    }

    public void updateRecord(){

        //System.out.println(contactFetch.getName());
        String name = nameET.getText().toString();
        String phoneNo = phoneNoET.getText().toString();

        contact = new Contact(name, phoneNo);
        manager.updateContact(idS,contact);

        listData();
    }

}
