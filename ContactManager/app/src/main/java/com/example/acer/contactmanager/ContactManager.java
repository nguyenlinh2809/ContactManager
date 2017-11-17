package com.example.acer.contactmanager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ACER on 11/17/2017.
 */

public class ContactManager {
    private Context context;
    private ArrayList<Contact> listContact;

    public ContactManager(Context context) {
        this.context = context;
    }

    public ArrayList<Contact> getListContact(){
        listContact = new ArrayList<>();
        String[] projections = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                ContactsContract.CommonDataKinds.Email.DATA
        };
        Cursor phone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projections, null, null, null);
        phone.moveToFirst();


        Cursor emailcursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Email.DISPLAY_NAME}, null, null, null);
        emailcursor.moveToFirst();
        int email_index = emailcursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME);
        while (emailcursor.moveToNext()){

            String e_mail = emailcursor.getString(email_index);
            Log.d("email", e_mail);
        }
        int nameIndex = phone.getColumnIndex(projections[0]);
        int numberIndex = phone.getColumnIndex(projections[1]);
        int photoIndex = phone.getColumnIndex(projections[2]);
        int mailIndex = phone.getColumnIndex(projections[3]);

        while (phone.moveToNext()){
            String name = phone.getString(nameIndex);
            String number = phone.getString(numberIndex);
            String photoUri = phone.getString(photoIndex);
            Bitmap bitmap = getBitmapFromUri(photoUri);
            String email = phone.getString(mailIndex);

            listContact.add(new Contact(name, email, number, bitmap));
        }
        Collections.sort(listContact);
        return listContact;
    }

    private Bitmap getBitmapFromUri(String photoUri) {
        if(photoUri != null){
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(photoUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }else return null;
    }
}
