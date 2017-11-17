package com.example.acer.contactmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ACER on 11/17/2017.
 */

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> listContact;
    Context context;
    int myLayout;

    public ContactAdapter(ArrayList<Contact> listContact, Context context, int myLayout) {
        this.listContact = listContact;
        this.context = context;
        this.myLayout = myLayout;
    }

    @Override
    public int getCount() {
        return listContact.size();
    }

    @Override
    public Object getItem(int i) {
        return listContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ViewHoler viewHoler;
        if(v == null){

            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(myLayout, null);

            viewHoler = new ViewHoler();
            viewHoler.imvAvatar = v.findViewById(R.id.imvAvatar);
            viewHoler.tvName = v.findViewById(R.id.tvName);
            viewHoler.tvPhone = v.findViewById(R.id.tvPhone);
            viewHoler.tvEmail = v.findViewById(R.id.tvEmail);
            viewHoler.imgbtnCall = v.findViewById(R.id.imgbtnCall);
            viewHoler.imgbtnSMS = v.findViewById(R.id.imgbtnSMS);

            v.setTag(viewHoler);
        }else viewHoler = (ViewHoler) v.getTag();


        final Contact contact = listContact.get(i);
        if(contact.getAvatar()!= null){
            viewHoler.imvAvatar.setImageBitmap(contact.getAvatar());
        }else viewHoler.imvAvatar.setImageResource(R.mipmap.ic_launcher);
        viewHoler.tvName.setText(contact.getName());
        viewHoler.tvPhone.setText(contact.getPhoneNumber());
        viewHoler.tvEmail.setText(contact.getEmail());
        viewHoler.imgbtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCall(contact.getPhoneNumber());
            }
        });

        viewHoler.imgbtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSms(contact.getPhoneNumber());
            }
        });
        return v;
    }

    private void doSms(String phoneNumber) {
        Toast.makeText(context, "Chưa làm "+phoneNumber, Toast.LENGTH_SHORT).show();
    }

    private void doCall(String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phoneNumber));
        context.startActivity(intent);
    }

    public class ViewHoler{
        private ImageView imvAvatar;
        private TextView tvName, tvPhone, tvEmail;
        private ImageButton imgbtnSMS, imgbtnCall;
    }
}
