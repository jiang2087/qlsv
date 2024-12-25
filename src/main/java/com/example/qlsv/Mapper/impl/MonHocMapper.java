package com.example.qlsv.Mapper.impl;

import com.example.qlsv.Mapper.IRowMappers;
import com.example.qlsv.model.MonHoc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MonHocMapper implements IRowMappers<MonHoc> {
    private static Class<?> clazz = MonHoc.class;

    @Override
    public MonHoc rowMapper(ResultSet rs) {
        MonHoc MonHoc = new MonHoc();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                System.out.println(name);
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
                setProperty(MonHoc, name, rs.getObject(name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return MonHoc;
    }

    @Override
    public void setProperty(MonHoc MonHoc, String fieldName, Object o) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(MonHoc, o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
