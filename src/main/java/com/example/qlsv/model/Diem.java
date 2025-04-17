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
    private Float diemTX1;
    private Float diemTX2;
    private Float diemCuoiKy;
    private Float diemGiuaKy;
    private Float diemTichLuy;
    private String xepLoai;
    private String maSV;
    private String maLop;
    private String maMH;
}
