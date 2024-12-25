package com.example.qlsv.AdminController;

import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.DAO.Impl.LopDAO;
import com.example.qlsv.model.Lop;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class QLLHAdminController {
    @FXML
    private TextField txtMaLop;
    @FXML
    private TextField txtTenLop;
    @FXML
    private TextField txtTenGiangVien;
    @FXML
    private TextField txtDiaDiem;
    @FXML
    private TextField txtThoiGian;
    @FXML
    private ComboBox<String> comBoMaMH;

    ILopDAO dao = new LopDAO();

    public void themBtnCLick(MouseEvent mouseEvent) {
        String maLop = txtMaLop.getText();
        String tenLop = txtTenLop.getText();
        String tenGV = txtTenGiangVien.getText();
        String diaDiem = txtDiaDiem.getText();
        String thoiGian = txtThoiGian.getText();
        String maMH = comBoMaMH.getValue();
        Lop tmp = Lop.builder().maLop(maLop)
                .diaDiem(diaDiem).thoiGianHoc(thoiGian).build();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Đã thêm lớp học thành công");

        // Hiển thị thông báo
        alert.showAndWait();
    }

    public void suaBtnClick(MouseEvent mouseEvent) {
    }

    public void xoaBtnClick(MouseEvent mouseEvent) {
    }
}
