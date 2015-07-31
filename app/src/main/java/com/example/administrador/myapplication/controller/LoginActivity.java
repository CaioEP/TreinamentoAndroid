package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.util.FormHelper;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText userName;
    EditText userPassword;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindFields();
        bindLoginButton();
    }

    private void bindFields() {
        userName = (EditText) findViewById(R.id.editTextUserName);
        userPassword = (EditText) findViewById(R.id.editTextPassword);
        userName.requestFocus();
    }

    private void bindLoginButton() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FormHelper.requireValidate(LoginActivity.this, userName, userPassword)) {
                    bindUser();
                    if (user.authentication()) {
                        Toast.makeText(LoginActivity.this, getString(R.string.authentication_cofirm_success), Toast.LENGTH_SHORT).show();
                        Intent goToMainActivity = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(goToMainActivity);
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.authentication_cofirm_fail), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void bindUser() {
        if (user == null) {
            user = new User();
        }
        user.setName(userName.getText().toString());
        user.setPassword(userName.getText().toString());
        clearFields();
    }

    private void clearFields() {
        userName.setText("");
        userPassword.setText("");
        userName.requestFocus();
    }
}
