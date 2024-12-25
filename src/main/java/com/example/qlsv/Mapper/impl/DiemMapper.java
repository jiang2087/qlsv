package com.example.qlsv.Mapper.impl;

import com.example.qlsv.Mapper.IRowMappers;
import com.example.qlsv.model.Diem;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DiemMapper implements IRowMappers<Diem> {
    private static Class<?> clazz = Diem.class;

    @Override
    public Diem rowMapper(ResultSet rs) {
        Diem diem = new Diem();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                System.out.println(name);
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
                setProperty(diem, name, rs.getObject(name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return diem;
    }

    @Override
    public void setProperty(Diem Diem, String fieldName, Object o) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(Diem, o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
