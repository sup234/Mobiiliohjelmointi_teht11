package com.example.teht11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnOpen;
    private Button btnClear;
    private ListView listview;
    private int resource;
    private String name;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 345;
    private final int REQUEST_OPEN_CONTACTS = 678;
    private final int DOWNLOAD_REQUEST_CODE = 1024;
    private ArrayList<String> lista = new ArrayList<String>();
    private CustomAdapter adapteri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        btnClear = findViewById(R.id.btnClear);
        listview = findViewById(R.id.listview1);
        adapteri = new CustomAdapter(this, R.layout.listview, name);
        listview.setAdapter(adapteri);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_OPEN_CONTACTS);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapteri.clear();
                lista.clear();
                adapteri.notifyDataSetChanged();
            }
        });

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Virhe!", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (REQUEST_OPEN_CONTACTS) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        if (!lista.contains(name)) {
                            lista.add(name);
                            adapteri.add(name);
                            adapteri.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Yhteystieto on jo listassa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }

}
