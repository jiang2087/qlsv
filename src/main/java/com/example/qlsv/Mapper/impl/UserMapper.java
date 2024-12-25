package com.example.qlsv.Mapper.impl;

import com.example.qlsv.Mapper.IRowMappers;
import com.example.qlsv.model.User;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserMapper implements IRowMappers<User> {
    private static Class<?> clazz = User.class;

    @Override
    public User rowMapper(ResultSet rs) {
        User User = new User();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                System.out.println(name);
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
                setProperty(User, name, rs.getObject(name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return User;
    }

    @Override
    public void setProperty(User User, String fieldName, Object o) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(User, o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
