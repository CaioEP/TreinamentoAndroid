package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Address;
import com.example.administrador.myapplication.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientContract {

    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String STATE = "state";

    public static final String[] COLUNS = {ID, NAME, AGE, CITY, STREET, STATE};

    public static String getSqlCreateTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ")
                .append(TABLE)
                .append(" ( ")
                .append(ID + " INTEGER PRIMARY KEY, ")
                .append(NAME + " TEXT, ")
                .append(AGE + " INTEGER, ")
                .append(CITY + " TEXT, ")
                .append(STREET + " TEXT, ")
                .append(STATE + " TEXT ")
                .append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.CITY, client.getAddress().getCity());
        values.put(ClientContract.STATE, client.getAddress().getState());
        values.put(ClientContract.STREET, client.getAddress().getStreet());
        return values;
    }

    public static Client getBindClient(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();

            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));

            Address address = new Address();
            address.setCity(cursor.getString(cursor.getColumnIndex(ClientContract.CITY)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(ClientContract.STREET)));
            address.setState(cursor.getString(cursor.getColumnIndex(ClientContract.STATE)));

            client.setAddress(address);

            return client;
        }
        return null;
    }

    public static List<Client> getBindClientList(Cursor cursor){
        List<Client> clients = new ArrayList<>();
        while(cursor.moveToNext()){
            clients.add(getBindClient(cursor));
        }
        return clients;
    }


}
