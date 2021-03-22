package com.college.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactPage extends MainActivity {

    EditText editFirstName;
    Button cancelButton, addButton;
    StoreContacts storeContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_page);

        editFirstName = (EditText) findViewById(R.id.firstNameEdit);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        addButton = (Button) findViewById(R.id.addButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("isContact", false);
                startActivity(i);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editFirstName.getText().toString().trim();
                if (!text.equals("")) {
                    Contact contact = new Contact(text);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("isContact", true);
                    i.putExtra("Contact", contact);
                    startActivity(i);
                } else {
                    editFirstName.setText("Enter a name");
                }
            }
        });
    }
}