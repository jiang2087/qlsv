package com.example.qlsv.DAO.Impl;

import com.example.qlsv.DAO.GenericDAO;

import com.example.qlsv.DAO.IUserDAO;
import com.example.qlsv.Mapper.impl.UserMapper;
import com.example.qlsv.model.User;

import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public List<User> findAll() {
        String query = """
                    SELECT maSV, diaChi, soDienThoai, taiKhoan, matKhau, hoTen, hinhAnh, quyen, gioiTinh, ngaySinh, email
                    FROM users
                """;
        return query(query, new UserMapper());
    }

    @Override
    public User findByMaSV(String maSV) {
        String query = """
                    SELECT maSV, diaChi, soDienThoai, taiKhoan, matKhau, hoTen, hinhAnh, quyen, gioiTinh, ngaySinh, email
                    FROM users WHERE maSV=?
                """;
        List<User> list = query(query, new UserMapper(), maSV);
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public List<User> findByName(String hoTen) {
        String query = """
                SELECT maSV, diaChi, soDienThoai, taiKhoan, matKhau, hoTen, hinhAnh, quyen, gioiTinh, ngaySinh, email
                FROM users WHERE hoTen LIKE ?
            """;
        return query(query, new UserMapper(), "%" + hoTen + "%"); // Truyền giá trị tham số vào
    }

    @Override
    public void updateSV(User user) {
        String query = """
                    UPDATE diaChi=?, soDienThoai=?, hoTen=?, hinhAnh=?, gioiTinh=?, ngaySinh=?, email=?
                    FROM users WHERE maSV=?
                """;
        update(query, user.getDiaChi(), user.getSoDienThoai(), user.getHoTen(), user.getHinhAnh(),
                user.getGioiTinh(), user.getNgaySinh(), user.getEmail(), user.getMaSV());
    }

    @Override
    public void deleteSV(String maSV) {
        String query = """
                    DELETE FROM users WHERE maSV=?
                """;
        delete(query, maSV);
    }

    @Override
    public List<User> findByClassId(String maLop) {
        String query = """
                    SELECT users.maSV, diaChi, soDienThoai, taiKhoan, matKhau, hoTen, hinhAnh, quyen, gioiTinh, ngaySinh, email FROM users
                    INNER JOIN users_lop AS ul ON ul.maSV = users.maSV INNER JOIN lop as l
                    ON ul.maLop = l.maLop WHERE l.maLop = ?;
                """;
        return query(query, new UserMapper(), maLop);
    }

    @Override
    public User login(String userName, String password) {
        String query = """
                SELECT maSV, diaChi, soDienThoai, taiKhoan, matKhau, hoTen, hinhAnh, quyen, gioiTinh, ngaySinh, email 
                FROM users 
                WHERE taiKhoan = ? AND matKhau = ?;
            """;
        List<User> list = query(query, new UserMapper(), userName, password);
        return list.isEmpty() ? null : list.get(0);
    }

    public static void main(String[] args) {

    }
}
