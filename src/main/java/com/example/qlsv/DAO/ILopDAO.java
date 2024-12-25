package com.example.qlsv.DAO;

import com.example.qlsv.model.Lop;

import java.util.List;

public interface ILopDAO {
    List<Lop> findAll();
    Lop findOne(String maLop);

}
