package com.example.administrador.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Address;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.persistence.MemoryClientRepository;

/**
 * Created by Administrador on 21/07/2015.
 */
public class ClientPersistActivity extends AppCompatActivity {
    EditText clientName;
    EditText clientAge;
    EditText clientAddressCity;
    EditText clientAddressState;
    EditText clientAddressStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);
        clientName = (EditText) findViewById(R.id.clientName);
        clientAge = (EditText) findViewById(R.id.clientAge);
        clientAddressCity = (EditText) findViewById(R.id.clientAddressCity);
        clientAddressState = (EditText) findViewById(R.id.clientAddressState);
        clientAddressStreet = (EditText) findViewById(R.id.clientAddressStreet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_save){
            bindClient().Save();
            Toast.makeText(ClientPersistActivity.this,Client.getAll().toString(),Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private Client bindClient(){
        Client client = new Client();
        client.setName(clientName.getText().toString());
        client.setAge(Integer.valueOf(clientAge.getText().toString()));
        Address address = new Address();
        address.setCity(clientAddressCity.getText().toString());
        address.setStreet(clientAddressStreet.getText().toString());
        address.setState(clientAddressState.getText().toString());
        client.setAddress(address);
        clearFields();
        return client;
    }

    private void clearFields() {
        clientName.setText("");
        clientAge.setText("");
        clientAddressState.setText("");
        clientAddressCity.setText("");
        clientAddressStreet.setText("");
        clientName.requestFocus();
    }
}
