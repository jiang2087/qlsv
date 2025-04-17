package com.example.qlsv.DAO.Impl;



import com.example.qlsv.DAO.GenericDAO;
import com.example.qlsv.Mapper.IRowMappers;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements GenericDAO<T> {
    ResourceBundle bundle = ResourceBundle.getBundle("db");

    public Connection getConnection() {
        Connection cnt = null;
        try {
            Class.forName(bundle.getString("driverName"));
            cnt = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }

    @Override
    public <T> List<T> query(String query, IRowMappers<T> rowMapper, Object... prm) {
        List<T> list = new ArrayList<>();
        Connection cnt = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            cnt = getConnection();
            pr = cnt.prepareStatement(query);
            setParameter(pr, prm);
            rs = pr.executeQuery();
            while (rs.next()) {
                list.add(rowMapper.rowMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(pr != null) pr.close();
                if(cnt != null) cnt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return list;
    }


    @Override
    public Integer insert(String query, Object... prm){
        int id = 0;
        Connection cnt = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            cnt = getConnection();
            pr = cnt.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            setParameter(pr, prm);
            pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(rs != null) rs.close();
                if(pr != null) pr.close();
                if(cnt != null) cnt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public void update(String query, Object... prm) {
        Connection cnt = null;
        PreparedStatement pr = null;
        try {
            cnt = getConnection();
            pr = cnt.prepareStatement(query);
            setParameter(pr, prm);
            pr.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(pr != null) pr.close();
                if(cnt != null) cnt.close();
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void delete(String query, Object... prm) {
        Connection cnt = null;
        PreparedStatement pr = null;
        try {
            cnt = getConnection();
            pr = cnt.prepareStatement(query);
            setParameter(pr, prm);
            pr.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(pr != null) pr.close();
                if(cnt != null) pr.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public int count(String query, Object... prm) {
        Connection cnt = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cnt = getConnection();
            pr = cnt.prepareStatement(query);
            setParameter(pr, prm);
            rs = pr.executeQuery();
            if(rs.next()) count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(pr != null) pr.close();
                if(cnt != null) cnt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return count;
    }

    public List<Integer> countArray(String query, Object... prm){
        Connection cnt = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        List<Integer> count = new ArrayList<>();
        try {
            cnt = getConnection();
            pr = cnt.prepareStatement(query);
            setParameter(pr, prm);
            rs = pr.executeQuery();
            while(rs.next()){
                count.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(pr != null) pr.close();
                if(cnt != null) cnt.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return count;
    }

    public void setParameter(PreparedStatement ps, Object... prm) throws SQLException {
        for (int i = 0; i < prm.length; i++) {
            if (prm[i] == null) {
                ps.setObject(i + 1, null);
                continue;
            }

            switch (prm[i].getClass().getSimpleName()) {
                case "String" -> ps.setString(i + 1, (String) prm[i]);
                case "Integer" -> ps.setInt(i + 1, (Integer) prm[i]);
                case "Double" -> ps.setDouble(i + 1, (Double) prm[i]);
                case "Boolean" -> ps.setBoolean(i + 1, (Boolean) prm[i]);
                case "Date" -> ps.setDate(i + 1, (Date) prm[i]);
                case "Timestamp" -> ps.setTimestamp(i + 1, (Timestamp) prm[i]);
                case "Long" -> ps.setLong(i + 1, (Long) prm[i]);
                case "Float" -> ps.setFloat(i + 1, (Float) prm[i]);
                case "BigDecimal" -> ps.setBigDecimal(i + 1, (BigDecimal) prm[i]);
                case "LocalDate" -> ps.setDate(i + 1, Date.valueOf((LocalDate) prm[i]));
                case "LocalDateTime" -> ps.setTimestamp(i + 1, Timestamp.valueOf((LocalDateTime) prm[i]));
                default -> throw new SQLException(
                        "Unsupported parameter type: " + prm[i].getClass().getName());
            }
        }
    }
}
