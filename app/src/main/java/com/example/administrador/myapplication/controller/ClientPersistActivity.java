package com.example.administrador.myapplication.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Address;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.persistence.MemoryClientRepository;
import com.example.administrador.myapplication.model.services.CepService;
import com.example.administrador.myapplication.util.FormHelper;

import java.util.concurrent.ExecutionException;

public class ClientPersistActivity extends AppCompatActivity {
    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private Client client;
    private EditText clientName;
    private EditText clientAge;
    private EditText clientAddressCity;
    private EditText clientAddressState;
    private EditText clientAddressStreet;
    private EditText clientAddressNeighborhood;
    private EditText clientAddressZipCode;
    private Button buttonFindZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);
        bindFields();
        getParameters();
    }

    private void getParameters() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if (client == null) {
                throw new IllegalArgumentException();
            }
            bindForm(client);
        }
    }

    private void bindFields() {
        clientName = (EditText) findViewById(R.id.clientName);
        clientAge = (EditText) findViewById(R.id.clientAge);
        clientAddressCity = (EditText) findViewById(R.id.clientAddressCity);
        clientAddressState = (EditText) findViewById(R.id.clientAddressState);
        clientAddressStreet = (EditText) findViewById(R.id.clientAddressStreet);
        clientAddressZipCode = (EditText) findViewById(R.id.editTextCep);
        clientAddressNeighborhood = (EditText) findViewById(R.id.clientAddressNeighborhood);
        bindButtonFindCep();
    }

    private void bindButtonFindCep() {
        buttonFindZipCode = (Button) findViewById(R.id.buttonFindCep);
        buttonFindZipCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String, Void, Address> execute = new GetAddressByCep().execute(clientAddressZipCode.getText().toString());

                try {
                    Address address = execute.get();
                    clientAddressCity.setText(address.getCity());
                    clientAddressState.setText(address.getState());
                    clientAddressStreet.setText(address.getStreet());
                    clientAddressZipCode.setText(address.getZipCode());
                    clientAddressNeighborhood.setText(address.getNeighborhood());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {


            if (FormHelper.requireValidate(ClientPersistActivity.this, clientName, clientAge, clientAddressCity, clientAddressState, clientAddressStreet,clientAddressNeighborhood, clientAddressZipCode)) {
                bindClient();
                client.Save();
                Toast.makeText(ClientPersistActivity.this, getString(R.string.save_confirm_message), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindClient() {
        if (client == null) {
            client = new Client();
        }
        client.setName(clientName.getText().toString());
        client.setAge(Integer.valueOf(clientAge.getText().toString()));
        Address address = new Address();
        address.setCity(clientAddressCity.getText().toString());
        address.setStreet(clientAddressStreet.getText().toString());
        address.setState(clientAddressState.getText().toString());
        address.setZipCode(clientAddressZipCode.getText().toString());
        address.setNeighborhood(clientAddressNeighborhood.getText().toString());
        client.setAddress(address);
        clearFields();
    }

    private void bindForm(Client client) {
        clientAddressState.setText(client.getAddress().getState());
        clientAddressStreet.setText(client.getAddress().getStreet());
        clientAddressCity.setText(client.getAddress().getCity());
        clientAddressZipCode.setText(client.getAddress().getZipCode());
        clientAddressNeighborhood.setText(client.getAddress().getNeighborhood());
        clientName.setText(client.getName());
        clientAge.setText(client.getAge().toString());
    }

    private void clearFields() {
        clientName.setText("");
        clientAge.setText("");
        clientAddressState.setText("");
        clientAddressCity.setText("");
        clientAddressStreet.setText("");
        clientAddressZipCode.setText("");
        clientAddressNeighborhood.setText("");
        clientName.requestFocus();
    }

    private class GetAddressByCep extends AsyncTask<String, Void, Address> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ClientPersistActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected Address doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            progressDialog.dismiss();
        }
    }
}
