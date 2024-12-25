package com.example.qlsv.DAO;

import com.example.qlsv.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> findAll();
    User findByMaSV(String maSV);
    List<User> findByName(String hoTen);
    void updateSV(User user);
    void deleteSV(String maSV);
    List<User> findByClassId(String maLop);
}
