package com.example.qlsv.Mapper;

import java.sql.ResultSet;

public interface IRowMappers<T> {

    T rowMapper(ResultSet rs);

    void setProperty(T t, String fieldName, Object o);
}
