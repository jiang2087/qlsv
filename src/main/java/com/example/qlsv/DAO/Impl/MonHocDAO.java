package com.example.qlsv.DAO.Impl;

import com.example.qlsv.DAO.IMonHoc;
import com.example.qlsv.Mapper.impl.MonHocMapper;
import com.example.qlsv.model.MonHoc;

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
        String query = """
                SELECT maMH, tenMonHoc, soTinChi, tinChiTH, tinChiLT, tinChiBTL, hocKy FROM monhoc
                """;
        List<MonHoc> list = query(query, new MonHocMapper());
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public void updateMH(MonHoc MH) {
        String query = """
                UPDATE tenMonHoc, soTinChi, tinChiTH, tinChiLT, tinChiBTL, hocKy 
                FROM monhoc WHERE maMH = ?
                """;
        update(query, MH.getTenMonHoc(), MH.getSoTinChi(), MH.getTinChiTH(), MH.getTinChiLT(), MH.getTinChiBTL(),
                MH.getHocKy(), MH.getMaMH());
    }

    @Override
    public void deleteMH(String maMH) {
        String query = """
                DELETE FROM monhoc WHERE maMH = ?;
                """;
        delete(query, maMH);
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

    public static void main(String[] args) {
        MonHoc a = MonHoc.builder()
                .hocKy(1).tenMonHoc("sadfefv").maMH("JVWEB").tinChiBTL(3)
                .build();
        IMonHoc dao = new MonHocDAO();
        dao.addMH(a);
    }
}
