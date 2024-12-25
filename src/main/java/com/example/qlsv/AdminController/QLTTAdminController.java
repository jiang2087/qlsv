package com.example.qlsv.AdminController;


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





    public void searchByNameClick(MouseEvent mouseEvent) {

    }

    public void clickSearchByClassBtnCLick(MouseEvent mouseEvent) {
    }
    // Method gọi API và cập nhật TableView
    public void loadSinhVienData() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSinhVienData();
    }
}
