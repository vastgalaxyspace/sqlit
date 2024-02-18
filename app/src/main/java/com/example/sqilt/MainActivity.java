package com.example.sqilt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sqilt.data.DatabaseHandler;
import com.example.sqilt.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db=new DatabaseHandler(MainActivity.this);

        Contact jermy=new Contact();
        jermy.setName("jeremy");
        jermy.setPhonenumber("234567890");

        Contact suraj=new Contact();
        jermy.setName("suraj");
        jermy.setPhonenumber("9999999999");

//        db.addcontact(jermy);

        Contact c=db.getContact(1);
        c.setName("newjereny");
        c.setPhonenumber("87348756596");

//        db.updatecontact(c);
        db.deletecontact(c);




        List<Contact> contactList= db.getallcontacts();

        for(Contact contact:contactList){
            Log.d("Mainactivity","oncreate"+ contact.getId());
        }
    }
}