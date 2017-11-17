package com.example.acer.contactmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    TabHost tabHost;
    ListView lvHistory, lvContact;
    ArrayList<Contact> listContact;
    ContactAdapter adapter;

    ContactManager contactManager;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, null, false);
        tabHost = view.findViewById(android.R.id.tabhost);
        lvContact = view.findViewById(R.id.lvContact);
        lvHistory = view.findViewById(R.id.lvHistory);

        contactManager = new ContactManager(getActivity());
        listContact = new ArrayList<>();
        adapter = new ContactAdapter(listContact, getActivity(), R.layout.row_contact);
        lvContact.setAdapter(adapter);
        loadTabs();
        loadContacts();
        return view;
    }

    private void loadContacts() {
        listContact.clear();
        listContact.addAll(contactManager.getListContact());
        adapter.notifyDataSetChanged();
    }

    private void loadTabs() {
        tabHost.setup();
        TabHost.TabSpec tabspec;

        tabspec = tabHost.newTabSpec("tab1");
        tabspec.setContent(R.id.tab1);
        tabspec.setIndicator("History");
        tabHost.addTab(tabspec);

        tabspec = tabHost.newTabSpec("tab2");
        tabspec.setContent(R.id.tab2);
        tabspec.setIndicator("Contact");
        tabHost.addTab(tabspec);

        tabHost.setCurrentTab(0);
    }

}
