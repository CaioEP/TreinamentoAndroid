package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Client> clients = getClients();

        ListView listViewClients = (ListView) findViewById(R.id.listViewClient);

        ClientListAdapter clientListAdapter = new ClientListAdapter(MainActivity.this, clients);

        listViewClients.setAdapter(clientListAdapter);

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
