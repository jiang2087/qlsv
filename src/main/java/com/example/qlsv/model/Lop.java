package com.example.qlsv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lop {
    private String maLop;
    private String tenLop;
    private String tenGiangVien;
    private String diaDiem;
    private String thoiGianHoc;
    private String maMH;
}
