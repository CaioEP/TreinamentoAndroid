package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

/**
 * Created by Administrador on 30/07/2015.
 */
public class SQLiteUserRepository implements BaseRepository<User> {

    private static SQLiteUserRepository singletonInstance;

    private SQLiteUserRepository() {
        super();
    }

    public static BaseRepository getInstance() {
        if (SQLiteUserRepository.singletonInstance == null) {
            SQLiteUserRepository.singletonInstance = new SQLiteUserRepository();
        }
        return SQLiteUserRepository.singletonInstance;
    }

    @Override
    public void save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = UserContract.getContentValues(user);
        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, values);
        } else {
            String where = UserContract.ID + " = ?";
            String[] args = {user.getId().toString()};
            db.update(UserContract.TABLE, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<User> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        return UserContract.getBindUserList(db.query(UserContract.TABLE, UserContract.COLUMNS, null, null, null, null, UserContract.NAME));
    }

    @Override
    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = UserContract.ID + " = ?";
        String[] args = {user.getId().toString()};
        db.delete(UserContract.TABLE, where, args);
    }

    public boolean authentication(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String whereClause = UserContract.NAME + " = ? AND " + UserContract.PASSWORD + " = ?";
        String[] whereArgs = new String[]{user.getName(), user.getPassword()};
        User result = UserContract.getBindUser(
                db.query(UserContract.TABLE, UserContract.COLUMNS, whereClause, whereArgs, null, null, null)
        );
        return result != null;
    }
}
