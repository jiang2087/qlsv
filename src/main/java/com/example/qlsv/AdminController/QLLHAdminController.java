package com.example.qlsv.AdminController;

import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.DAO.Impl.LopDAO;
import com.example.qlsv.model.Lop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class QLLHAdminController {
    @FXML private TableView<Lop> tableLop;
    @FXML private TableColumn<Lop, String> colSTT;
    @FXML private TableColumn<Lop, String> colMaLop;
    @FXML private TableColumn<Lop, String> colTenLop;
    @FXML private TableColumn<Lop, String> colTenGiangVien;
    @FXML private TableColumn<Lop, String> colDiaDiem;
    @FXML private TableColumn<Lop, String> colThoiGian;
    @FXML private TableColumn<Lop, String> colMaMH;
    private ObservableList<Lop> lopList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Cập nhật các cột với dữ liệu trong lớp Lop
        colSTT.setCellValueFactory(new PropertyValueFactory<>("stt"));
        colMaLop.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        colTenLop.setCellValueFactory(new PropertyValueFactory<>("tenLop"));
        colTenGiangVien.setCellValueFactory(new PropertyValueFactory<>("tenGiangVien"));
        colDiaDiem.setCellValueFactory(new PropertyValueFactory<>("diaDiem"));
        colThoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGianHoc"));
        colMaMH.setCellValueFactory(new PropertyValueFactory<>("maMH"));

        // Lấy danh sách lớp học từ DAO và thêm vào TableView
        LopDAO dao = new LopDAO();
        lopList.clear();
        lopList.addAll(dao.findAll());
        tableLop.setItems(lopList);
    }
    ILopDAO dao = new LopDAO();


}
