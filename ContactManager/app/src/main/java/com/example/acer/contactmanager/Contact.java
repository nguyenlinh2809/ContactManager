package com.example.acer.contactmanager;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by ACER on 11/17/2017.
 */

public class Contact implements Comparable<Contact>{
    private String name, email;
    private String phoneNumber;
    private Bitmap avatar;

    public Contact(String name, String email, String phoneNumber, Bitmap avatar) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }


    @Override
    public int compareTo(@NonNull Contact contact) {
        return this.name.compareTo(contact.name);
    }
}