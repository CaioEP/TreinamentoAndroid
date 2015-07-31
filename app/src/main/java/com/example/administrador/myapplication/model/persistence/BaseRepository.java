package com.example.administrador.myapplication.model.persistence;

import com.example.administrador.myapplication.model.entities.Client;

import java.util.List;

public interface BaseRepository<T> {

    void save(T obj);

    List<T> getAll();

    void delete(T obj);

    boolean authentication(T obj);

}
