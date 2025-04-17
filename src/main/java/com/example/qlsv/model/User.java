package com.example.qlsv.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String maSV;
    private String diaChi;
    private String soDienThoai;
    private String taiKhoan;
    private String hoTen;
    private String hinhAnh;
    private boolean quyen;
    private String gioiTinh;
    private Date ngaySinh;
    private String email;
    private String matKhau;

}
