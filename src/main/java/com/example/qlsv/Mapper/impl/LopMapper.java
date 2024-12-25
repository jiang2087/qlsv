package com.example.qlsv.Mapper.impl;

import com.example.qlsv.Mapper.IRowMappers;
import com.example.qlsv.model.Lop;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class LopMapper implements IRowMappers<Lop> {
    private static Class<?> clazz = Lop.class;

    @Override
    public Lop rowMapper(ResultSet rs) {
        Lop Lop = new Lop();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                System.out.println(name);
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
                setProperty(Lop, name, rs.getObject(name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Lop;
    }

    @Override
    public void setProperty(Lop Lop, String fieldName, Object o) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(Lop, o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}