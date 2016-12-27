package com.senapathi.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
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

        mRef = new Firebase("https://fir-a3c8a.firebaseio.com/");
        Button send_button = (Button) findViewById(R.id.sendBtn);
        Button receive_button = (Button) findViewById(R.id.receivebtn);
        send_button.setOnClickListener(this);
        receive_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.sendBtn:
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
            break;
            case R.id.receivebtn:
                Intent intent = new Intent(MainActivity.this,AuthActivity.class);
                startActivity(intent);
                break;
        }
    }
}
