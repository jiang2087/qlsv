package com.example.qlsv.DAO.Impl;

import com.example.qlsv.DAO.GenericDAO;
import com.example.qlsv.DAO.IDiemDAO;
import com.example.qlsv.Mapper.impl.DiemMapper;
import com.example.qlsv.model.Diem;

import java.util.List;

public class DiemDAO extends AbstractDAO<Diem> implements IDiemDAO {
    @Override
    public List<Diem> findAllInClass(String ClassId) {
        String query = """
                SELECT diemTX1, diemTX2, diemGiuaKy, diemCuoiKy, diemTichLuy, xepLoai, maSV, maLop, maMH FROM diem
                WHERE maLop = ?;
                """;
        return query(query, new DiemMapper(), ClassId);
    }

    @Override
    public Diem findOneByClass(String ClassId, String StudentId) {
        String query = """
                SELECT diemTX1, diemTX2, diemGiuaKy, diemCuoiKy, diemTichLuy, xepLoai, maSV, maLop, maMH FROM diem
                WHERE maLop = ? and maSV = ?;
                """;
        List<Diem> list = query(query, new DiemMapper(), ClassId, StudentId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Diem> findByMaSV(String maSV) {
        String query = """
                SELECT diemTX1, diemTX2, diemGiuaKy, diemCuoiKy, diemTichLuy, xepLoai, maSV, maLop, maMH FROM diem
                WHERE maSV=?;
                """;
        return query(query, new DiemMapper(), maSV);
    }

    @Override
    public void updateDiem(Diem diem) {
        String query = """
                UPDATE diem SET diemTX1=?, diemTX2=?, diemGiuaKy=?, diemCuoiKy=?, diemTichLuy=? 
                WHERE maSV=? and maLop =?;
                """;
        update(query, diem.getDiemTX1(), diem.getDiemTX2(), diem.getDiemGiuaKy(), diem.getDiemCuoiKy(),
                diem.getDiemTichLuy(), diem.getMaSV(), diem.getMaLop());
    }


    public static void main(String[] args) {
        IDiemDAO dao = new DiemDAO();
        List<Diem> diem = dao.findAllInClass("1");
        System.out.println(diem.get(0));
    }
}
