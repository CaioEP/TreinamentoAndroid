package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Address;
import com.example.administrador.myapplication.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientContract extends BaseContract{

    public static final String TABLE = "client";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String STATE = "state";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String ZIPCODE = "zipcode";
    public static final String STREETTYPE = "street_type";
    private static final String PHONE = "phone";
    public static final String[] COLUMNS = {ID, NAME, AGE, PHONE, CITY, STREET, STATE, NEIGHBORHOOD,ZIPCODE,STREETTYPE};


    public static String getSqlCreateTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ")
                .append(TABLE)
                .append(" ( ")
                .append(ID + " INTEGER PRIMARY KEY, ")
                .append(NAME + " TEXT, ")
                .append(AGE + " INTEGER, ")
                .append(PHONE + " TEXT, ")
                .append(CITY + " TEXT, ")
                .append(STREET + " TEXT, ")
                .append(STATE + " TEXT, ")
                .append(NEIGHBORHOOD + " TEXT, ")
                .append(STREETTYPE + " TEXT, ")
                .append(ZIPCODE + " TEXT ")
                .append(" );");
        return sql.toString();
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.PHONE, client.getPhone());
        values.put(ClientContract.CITY, client.getAddress().getCity());
        values.put(ClientContract.STATE, client.getAddress().getState());
        values.put(ClientContract.STREET, client.getAddress().getStreet());
        values.put(ClientContract.ZIPCODE, client.getAddress().getZipCode());
        values.put(ClientContract.STREETTYPE, client.getAddress().getStreetType());
        values.put(ClientContract.NEIGHBORHOOD, client.getAddress().getNeighborhood());
        return values;
    }

    public static Client getBindClient(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();

            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));

            Address address = new Address();
            address.setCity(cursor.getString(cursor.getColumnIndex(ClientContract.CITY)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(ClientContract.STREET)));
            address.setState(cursor.getString(cursor.getColumnIndex(ClientContract.STATE)));
            address.setZipCode(cursor.getString(cursor.getColumnIndex(ClientContract.ZIPCODE)));
            address.setStreetType(cursor.getString(cursor.getColumnIndex(ClientContract.STREETTYPE)));
            address.setNeighborhood(cursor.getString(cursor.getColumnIndex(ClientContract.NEIGHBORHOOD)));


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
