package com.example.qlsv.DAO;

import com.example.qlsv.model.Diem;

import java.util.List;

public interface IDiemDAO {
    List<Diem> findAllInClass(String ClassId);
    Diem findOneByClass(String ClassId, String StudentId);
}
