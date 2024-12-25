package com.example.qlsv.DAO;



import com.example.qlsv.Mapper.IRowMappers;

import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String query, IRowMappers<T> rowMapper, Object... prm);
    Integer insert(String query, Object... prm);
    void update(String query, Object... prm);
    void delete(String query, Object... prm);
    int count(String query, Object... prm);
    List<Integer> countArray(String query, Object... prm);
}
