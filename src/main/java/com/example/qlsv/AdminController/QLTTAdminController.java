package com.example.qlsv.AdminController;

import com.example.qlsv.Api.ApiUser;
import com.example.qlsv.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QLTTAdminController implements Initializable {

    @FXML
    private TableView<User> tblTTSV;
    @FXML
    private TableColumn<User, String> maSvColumn;
    @FXML
    private TableColumn<User, String> hoTenColumn;
    @FXML
    private TableColumn<User, String> gioiTinhColumn;
    @FXML
    private TableColumn<User, String> ngaySinhColumn;
    @FXML
    private TableColumn<User, String> diachiColumn;
    @FXML
    private TableColumn<User, String> soDienThoaiColumn;
    @FXML
    private TableColumn<User, String> emailColumn;

    private ApiUser apiUser = new ApiUser();

    public void searchByNameClick(MouseEvent mouseEvent) {

    }

    public void clickSearchByClassBtnCLick(MouseEvent mouseEvent) {
    }
    // Method gọi API và cập nhật TableView
    public void loadSinhVienData() {
        try {
            List<User> sinhVienList = apiUser.getSinhVienList();
            ObservableList<User> sinhVienObservableList = FXCollections.observableArrayList(sinhVienList);

            // Liên kết dữ liệu với các cột trong TableView
            maSvColumn.setCellValueFactory(new PropertyValueFactory<>("maSv"));
            hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
            gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
            ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
            diachiColumn.setCellValueFactory(new PropertyValueFactory<>("diachi"));
            soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            // Cập nhật dữ liệu vào TableView
            tblTTSV.setItems(sinhVienObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSinhVienData();
    }
}
