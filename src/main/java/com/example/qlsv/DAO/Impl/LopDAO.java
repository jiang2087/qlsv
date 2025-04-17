package com.example.qlsv.DAO.Impl;

import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.Mapper.impl.LopMapper;
import com.example.qlsv.model.Lop;

import java.util.List;

public class LopDAO extends AbstractDAO<Lop> implements ILopDAO {
    @Override
    public List<Lop> findAll() {
        String query = """
                   SELECT maLop, tenLop, tenGiangVien, diaDiem, thoiGianHoc, maMH
                   FROM lop;
                """;
        return query(query, new LopMapper());
    }

    @Override
    public Lop findOne(String maLop) {
        String query = """
                   SELECT maLop, tenLop, tenGiangVien, diaDiem, thoiGianHoc, maMH
                   FROM lop WHERE maLop=?;
                """;
        List<Lop> list =  query(query, new LopMapper(), maLop);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void updateLop(Lop lop) {
        String query = """
                   UPDATE lop SET tenLop=?, tenGiangVien=?, diaDiem=?, thoiGianHoc=?, maMH=?
                   WHERE maLop=?;
                """;
       update(query, lop.getTenLop(), lop.getTenGiangVien(), lop.getDiaDiem(), lop.getThoiGianHoc(),
               lop.getMaMH(), lop.getMaLop());
    }

    @Override
    public void deleteLop(String maLop) {
        String query = """
                    DELETE FROM lop WHERE maLop = ?;
                """;
        delete(query, maLop);
    }

    @Override
    public void addLop(Lop lop) {
        String query = """
                    INSERT INTO lop(maLop, tenLop, tenGiangVien, diaDiem, thoiGianHoc, maMH) VALUES
                    (?,?,?,?,?,?);
                """;
        insert(query, lop.getMaLop(), lop.getTenLop(), lop.getTenGiangVien(), lop.getDiaDiem(), lop.getThoiGianHoc(), lop.getMaMH());
    }

    @Override
    public List<Lop> findByMaSV(String maSV) {
        String query = """
                   SELECT lop.maLop, tenLop, tenGiangVien, diaDiem, thoiGianHoc, maMH
                   FROM lop INNER JOIN users_lop as ul on ul.maLop = lop.maLop
                   INNER JOIN users as ur on ur.maSV = ul.maSV WHERE ur.maSV = ?;
                """;
        return query(query, new LopMapper(), maSV);
    }

    @Override
    public int count() {
        String query = """
                    SELECT count(*) from lop;
                """;
        return count(query);
    }
}
