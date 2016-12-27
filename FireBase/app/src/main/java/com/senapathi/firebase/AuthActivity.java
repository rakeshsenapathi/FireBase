package com.senapathi.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Senapathi on 27-12-2016.
 */

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmailField;
    private EditText mPwdField;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.signinlayout);
        mEmailField = (EditText) findViewById(R.id.emailedt);
        mPwdField = (EditText) findViewById(R.id.pwdedt);
        Button loginbutton = (Button) findViewById(R.id.loginbtn);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(AuthActivity.this, AccountActivity.class));
                }
            }
        };

        loginbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        startsignin();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startsignin() {

        String email = mEmailField.getText().toString();
        String password = mPwdField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(AuthActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(AuthActivity.this, "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
