package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Client> clients = Client.getAll();

        ListView listViewClients = (ListView) findViewById(R.id.listViewClient);

        ClientListAdapter clientListAdapter = new ClientListAdapter(MainActivity.this, clients);

        listViewClients.setAdapter(clientListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            Intent goToClientPersistActivity = new Intent(MainActivity.this, ClientPersistActivity.class);
            startActivity(goToClientPersistActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Client> getClients() {
        List<Client> clients = new ArrayList<Client>();

        Client client1 = new Client();
        client1.setName("Caio");
        client1.setAge(20);

        Client client2 = new Client();
        client2.setName("Bruna");
        client2.setAge(19);

        clients.add(client1);
        clients.add(client2);

        return clients;
    }
}
