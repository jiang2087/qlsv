package com.example.qlsv.DAO.Impl;

import com.example.qlsv.DAO.IMonHoc;
import com.example.qlsv.Mapper.impl.MonHocMapper;
import com.example.qlsv.model.MonHoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class MonHocDAO extends AbstractDAO<MonHoc> implements IMonHoc {

    @Override
    public List<MonHoc> findAll() {

        String query = """
                SELECT maMH, tenMonHoc, soTinChi, tinChiTH, tinChiLT, tinChiBTL, hocKy FROM monhoc
                """;
        return query(query, new MonHocMapper());
    }

    @Override
    public MonHoc findOne(String maMH) {
        return null;
    }

    @Override
    public void updateMH(MonHoc MH) {

    }

    @Override
    public void deleteMH(String maMH) {

    }


    @Override
    public void addMH(MonHoc MH) {
        String query = """
                INSERT INTO monhoc(maMH, tenMonHoc, soTinChi, tinChiTH, tinChiLT, tinChiBTL, hocKy)
                VALUES (?,?,?,?,?,?,?);
                """;
        insert(query, MH.getMaMH(), MH.getTenMonHoc(), MH.getSoTinChi(), MH.getTinChiTH(), MH.getTinChiLT(),
                MH.getTinChiBTL(), MH.getHocKy());
    }



}
