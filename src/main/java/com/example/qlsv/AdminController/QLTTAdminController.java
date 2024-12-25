package com.example.qlsv.AdminController;


import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.DAO.IMonHoc;
import com.example.qlsv.DAO.IUserDAO;
import com.example.qlsv.DAO.Impl.LopDAO;
import com.example.qlsv.DAO.Impl.MonHocDAO;
import com.example.qlsv.DAO.Impl.UserDAO;
import com.example.qlsv.model.Lop;
import com.example.qlsv.model.MonHoc;
import com.example.qlsv.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QLTTAdminController{
    @FXML
    private ComboBox<String> comBoxMaLop;
    @FXML
    private TextField txtName;
    @FXML private TableView<User> tblTTSV;
    @FXML private TableColumn<User, String> sttColumn;
    @FXML private TableColumn<User, String> maSvColumn;
    @FXML private TableColumn<User, String> hoTenColumn;
    @FXML private TableColumn<User, String> gioiTinhColumn;
    @FXML private TableColumn<User, String> ngaySinhColumn;
    @FXML private TableColumn<User, String> diachiColumn;
    @FXML private TableColumn<User, String> soDienThoaiColumn;
    @FXML private TableColumn<User, String> emailColumn;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    private ILopDAO maLop = new LopDAO();
    @FXML
    public void initialize() {
        // Cập nhật các cột với dữ liệu trong đối tượng User
        sttColumn.setCellValueFactory(new PropertyValueFactory<>("stt"));
        maSvColumn.setCellValueFactory(new PropertyValueFactory<>("maSV"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        diachiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        sttColumn.setCellValueFactory(cellData -> {
            int index = userList.indexOf(cellData.getValue());
            return new SimpleStringProperty(String.valueOf(index + 1)); // Cộng thêm 1 để bắt đầu từ 1
        });

        List<Lop> lops = maLop.findAll();
        List<String> maLopList = new ArrayList<>();
        for(Lop lop :lops) {
            System.out.println(lop.getMaLop());
            maLopList.add(lop.getMaLop());
        }
        comBoxMaLop.getItems().addAll(maLopList);



        // Lấy danh sách sinh viên từ DAO và thêm vào TableView
        UserDAO dao = new UserDAO();


        userList.clear();
        userList.addAll(dao.findAll());
        // Gán danh sách vào TableView
        tblTTSV.setItems(userList);
    }
//    private void searchByClassIdClick() {
//        // Lấy mã lớp từ ComboBox
//        String maLop = comBoxMaLop.getValue();
//        if (maLop != null && !maLop.isEmpty()) {
//            // Tìm kiếm theo mã lớp
//            IUserDAO dao = new UserDAO();
//            List<User> result = dao.find(maLop);  // Giả sử findByClassId là phương thức tìm kiếm theo mã lớp
//            userList = FXCollections.observableArrayList(result);
//            tblTTSV.setItems(userList);
//        } else {
//            // Nếu không có mã lớp, có thể đưa thông báo hoặc để trống
//            System.out.println("Vui lòng chọn mã lớp.");
//        }
//    }



}
