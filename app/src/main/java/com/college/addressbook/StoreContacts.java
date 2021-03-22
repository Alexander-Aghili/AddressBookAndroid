package com.college.addressbook;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreContacts extends Service {

    private final IBinder mBinder = new StoreContactsBinder();
    private static ArrayList<Contact> contacts;

    public StoreContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
    }

    public class StoreContactsBinder extends Binder {
        StoreContacts getService() {
            return StoreContacts.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }

    public static ArrayList<String> getContactString() {
        ArrayList<String> contactStrings = new ArrayList<>();
        if (contacts == null)
            return null;
        for (int i = 0; i < contacts.size(); i++) {
            contactStrings.add("Contact " + (i+1) + ": " + contacts.get(i).toString());
        }
        return contactStrings;
    }
}
