package com.example.qlsv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonHoc {

    private  String maMH;
    private String tenMonHoc;
    private int soTinChi;
    private int tinChiTH;
    private int tinChiLT;
    private int tinChiBTL;
    private int hocKy;

}
