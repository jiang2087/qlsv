package com.example.qlsv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diem {
    private float diemTX1;
    private float diemTX2;
    private float diemcuoiKy;
    private float diemGiuaKy;
    private float diemTichLuy;
    private String xepLoai;
    private String maSV;
    private String maLop;
    private String maMH;
}
