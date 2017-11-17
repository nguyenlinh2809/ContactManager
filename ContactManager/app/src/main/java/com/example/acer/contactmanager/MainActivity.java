package com.example.acer.contactmanager;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationMenu;
    Toolbar toolbarMain;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addActionBar();
        addEvents();
    }

    private void addActionBar() {
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarMain.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        navigationMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_contact:
                        drawerLayout.closeDrawer(Gravity.START);
                        getSupportActionBar().setTitle("Contacts");
                        showContactFragment();
                        break;
                    case R.id.menu_setting:
                        drawerLayout.closeDrawer(Gravity.START);
                        getSupportActionBar().setTitle("Settings");
                        showSettingFragment();
                        break;
                }
                return false;
            }
        });
    }

    private void showSettingFragment() {
        SettingFragment settingFragmnet = new SettingFragment();
        fragmentManager.beginTransaction().replace(R.id.frameContent, settingFragmnet, getClass().getName()).commit();
    }

    private void showContactFragment() {
        ContactFragment contactFragment = new ContactFragment();
        fragmentManager.beginTransaction().replace(R.id.frameContent, contactFragment, getClass().getName()).commit();
    }

    private void addEvents() {

    }

    private void addControls() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationMenu = (NavigationView) findViewById(R.id.navigationMenu);
        toolbarMain = (Toolbar) findViewById(R.id.toolbarMain);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameContent, new ContactFragment(), getClass().getName()).commit();
    }
}
