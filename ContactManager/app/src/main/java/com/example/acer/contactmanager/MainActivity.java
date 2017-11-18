package com.example.acer.contactmanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements callPhone{
    public static String PERMISSION_READ_CONTACT = Manifest.permission.READ_CONTACTS;
    public static String PERMISSION_WRITE_CONTACT = Manifest.permission.WRITE_CONTACTS;
    public static String PERMISSION_PHONE_CALL = Manifest.permission.CALL_PHONE;
    public static int PERMISSION_CODE = 12340;
    DrawerLayout drawerLayout;
    NavigationView navigationMenu;
    Toolbar toolbarMain;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPermission();

    }

    private void addPermission() {
        if(!checkPermission(PERMISSION_READ_CONTACT)){
            requestPermissions(new String[]{PERMISSION_READ_CONTACT}, PERMISSION_CODE);
        }else {
            addControls();
            addActionBar();
            addEvents();
        }
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

    public boolean checkPermission(String permission){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else return false;
        }else{
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((requestCode == PERMISSION_CODE) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            addControls();
            addActionBar();
            addEvents();
        }else {
            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @Override
    public void doCall(Contact contact) {
        if(!checkPermission(PERMISSION_PHONE_CALL)){
            requestPermissions(new String[]{PERMISSION_PHONE_CALL}, PERMISSION_CODE);
            Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            doingCall(contact.getPhoneNumber());
        }else {
            doingCall(contact.getPhoneNumber());
        }
    }
    private void doingCall(String phoneNumber) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}
