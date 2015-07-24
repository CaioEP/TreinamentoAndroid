package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.Address;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class SQLiteClientRepository implements ClientRepository{

   private static SQLiteClientRepository singletonInstance;

    private SQLiteClientRepository() {
        super();
    }

    public static ClientRepository getInstance(){
        if(SQLiteClientRepository.singletonInstance == null){
            SQLiteClientRepository.singletonInstance = new SQLiteClientRepository();
        }
        return SQLiteClientRepository.singletonInstance;
    }

    @Override
    public void save(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = ClientContract.getContentValues(client);
        if(client.getId()==null){
            db.insert(ClientContract.TABLE, null, values);
        } else {
            String where = ClientContract.ID + " = ?";
            String[] args = {client.getId().toString()};
            db.update(ClientContract.TABLE, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<Client> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        return ClientContract.getBindClientList(db.query(ClientContract.TABLE, ClientContract.COLUNS, null, null, null, null, ClientContract.NAME));
    }

    @Override
    public void delete(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = ClientContract.ID + " = ?";
        String[] args = {client.getId().toString()};
        db.delete(ClientContract.TABLE,where,args);
    }
}
