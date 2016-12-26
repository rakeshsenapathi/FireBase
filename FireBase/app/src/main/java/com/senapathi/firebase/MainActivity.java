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
        //Firebase mChild = mRef.child("Name");
        EditText key = (EditText) findViewById(R.id.key_edt);
        EditText value = (EditText) findViewById(R.id.value_edt);
        String key_str = String.valueOf(key.getText());
        String value_str = String.valueOf(value.getText());
        if (!(key_str.isEmpty() && value_str.isEmpty())) {
            Firebase mChild = mRef.child(key_str);
            mChild.setValue(value_str);
            Toast.makeText(MainActivity.this, "Details Sent", Toast.LENGTH_SHORT).show();
            key.setText("");
            value.setText("");
        } else
            Toast.makeText(MainActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();

    }
}
