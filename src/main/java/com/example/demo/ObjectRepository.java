package com.example.demo;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface ObjectRepository<T> {

    void create(T t) throws ExecutionException, InterruptedException;

    T read(String primaryKey) throws ExecutionException, InterruptedException;

    T[] readAll() throws InterruptedException, ExecutionException;

    void update(T t, Map<String, Object> fields) throws InterruptedException, ExecutionException;

    void delete(T t) throws InterruptedException, ExecutionException;

}
