package com.example.administrador.myapplication.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.persistence.MemoryClientRepository;

import java.util.ArrayList;
import java.util.List;


public class ClientListActivity extends AppCompatActivity {
    private ListView listViewClients;
    private Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindClientList();
    }

    private void bindClientList() {
        List<Client> clients = Client.getAll();
        listViewClients = (ListView) findViewById(R.id.listViewClient);
        ClientListAdapter clientListAdapter = new ClientListAdapter(ClientListActivity.this, clients);
        listViewClients.setAdapter(clientListAdapter);
        listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);
                return false;
            }
        });
        registerForContextMenu(listViewClients);
    }

    @Override
    protected void onResume() {
        refreshClientList();
        super.onResume();
    }

    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClients.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent goToClientPersistActivity = new Intent(ClientListActivity.this, ClientPersistActivity.class);
            startActivity(goToClientPersistActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.menu_client_list_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            Intent intent = new Intent(ClientListActivity.this, ClientPersistActivity.class);
            intent.putExtra(ClientPersistActivity.CLIENT_PARAM,(Parcelable) client);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menu_delete){

            new AlertDialog.Builder(ClientListActivity.this).setMessage(R.string.confirm_progress).setTitle(R.string.title_delete_confirm).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    client.delete();
                    Toast.makeText(ClientListActivity.this, getString(R.string.delete_confirm_message), Toast.LENGTH_SHORT).show();
                    refreshClientList();
                }
            }).setNegativeButton(R.string.no, null).show();
        }
        return super.onContextItemSelected(item);
    }
}
