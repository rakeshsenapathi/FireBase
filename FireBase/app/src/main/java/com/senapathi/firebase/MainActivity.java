package com.senapathi.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this); // set the Firebase context to the application context to use anywhere

        mRef = new Firebase("https://fir-a3c8a.firebaseio.com/");
        Button send_button = (Button) findViewById(R.id.sendBtn);

        send_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Firebase mChild = mRef.child("Name");
        EditText name = (EditText) findViewById(R.id.name_edt);
        String name_str = name.getText().toString();
        if (!name_str.isEmpty()) {
            mChild.setValue(name_str);
            Toast.makeText(MainActivity.this, "Details Sent", Toast.LENGTH_SHORT).show();
            name.setText(null);
        } else if (name_str.isEmpty())
            Toast.makeText(MainActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();

    }
}
