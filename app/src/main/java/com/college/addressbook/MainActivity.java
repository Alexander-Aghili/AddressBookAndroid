package com.college.addressbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import com.college.addressbook.StoreContacts.StoreContactsBinder;

public class MainActivity extends AppCompatActivity {

    private StoreContacts storeContacts;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private Button addContact;
    boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        addContact = (Button) findViewById(R.id.addContactButton);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, StoreContacts.getContactString());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getBoolean("isContact")) {
                Contact contact = (Contact) extras.getSerializable("Contact");
                StoreContacts.addContact(contact);
            }
            listView.setAdapter(arrayAdapter);
            arrayAdapter.clear();
            arrayAdapter.addAll(StoreContacts.getContactString());
        }

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddContactPage.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, StoreContacts.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isConnected) {
            unbindService(serviceConnection);
            isConnected = false;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StoreContactsBinder factBinder = (StoreContactsBinder) service;
            storeContacts = factBinder.getService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };
}