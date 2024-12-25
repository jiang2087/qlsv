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
                   SELECT maLop, tenLop,  tenGiangVien, diaDiem, thoiGianHoc, maMH
                   FROM lop WHERE maLop=?;
                """;
        List<Lop> list =  query(query, new LopMapper(), maLop);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void updateLop(Lop lop) {
        String query = """
                   UPDATE tenLop=?, tenGiangVien=?, diaDiem=?, thoiGianHoc=?, maMH=?
                   FROM lop WHERE maLop=?;
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
}
