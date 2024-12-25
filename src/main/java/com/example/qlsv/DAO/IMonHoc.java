package com.example.qlsv.DAO;

import com.example.qlsv.model.MonHoc;

import java.util.List;

public interface IMonHoc {
    List<MonHoc> findAll();
    MonHoc findOne(String maMH);
    void updateMH(MonHoc MH);
    void deleteMH(String maMH);
    void addMH(MonHoc MH);
}
