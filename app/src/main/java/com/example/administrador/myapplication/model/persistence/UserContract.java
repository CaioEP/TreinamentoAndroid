package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 30/07/2015.
 */
public class UserContract extends BaseContract{
    public static final String TABLE = "user";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String[] COLUMNS = {ID, NAME, PASSWORD};

    public static String getSqlCreateTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ")
                .append(TABLE)
                .append(" ( ")
                .append(ID + " INTEGER PRIMARY KEY, ")
                .append(NAME + " TEXT, ")
                .append(PASSWORD + " TEXT ")
                .append(" );");
        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.ID, user.getId());
        values.put(UserContract.NAME, user.getName());
        values.put(UserContract.PASSWORD, user.getPassword());
        return values;
    }

    public static ContentValues initialInsert(){
        ContentValues values = new ContentValues();
        User user = new User();

        user.setId(1);
        user.setName("admin");
        user.setPassword("admin");

        values.put(ID, user.getId());
        values.put(NAME, user.getName());
        values.put(PASSWORD, user.getPassword());

        return values;
    }

    public static User getBindUser(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();

            user.setId(cursor.getInt(cursor.getColumnIndex(UserContract.ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(UserContract.NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> getBindUserList(Cursor cursor){
        List<User> users = new ArrayList<>();
        while(cursor.moveToNext()){
            users.add(getBindUser(cursor));
        }
        return users;
    }
}
