package com.example.qlsv.DAO.Impl;

import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.Mapper.impl.LopMapper;
import com.example.qlsv.model.Lop;

import java.util.List;

public class LopDAO extends AbstractDAO<Lop> implements ILopDAO {
    @Override
    public List<Lop> findAll() {
        String query = """
                   SELECT maLop, tenLop, hocKy, tenGiangVien, diaDiem, thoiGianHoc, maMH
                   FROM lop;
                """;
        return query(query, new LopMapper());
    }

    @Override
    public Lop findOne(String maLop) {
        return null;
    }
}
