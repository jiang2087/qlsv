package com.example.qlsv.AdminController;

import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.DAO.IMonHoc;
import com.example.qlsv.DAO.Impl.LopDAO;
import com.example.qlsv.DAO.Impl.MonHocDAO;
import com.example.qlsv.model.Lop;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;

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
    @FXML
    private TableView<Lop> tblLop;
    @FXML
    private TableColumn<Lop, String> colSTT;
    @FXML
    private TableColumn<Lop, String> colMaLop;
    @FXML
    private TableColumn<Lop, String> colTenLop;
    @FXML
    private TableColumn<Lop, String> colTenGV;
    @FXML
    private TableColumn<Lop, String> colDiaDiem;
    @FXML
    private TableColumn<Lop, String> colThoiGian;
    @FXML
    private TableColumn<Lop, String> colMaMH;
    private ObservableList<Lop> lopList;

    ILopDAO dao = new LopDAO();
    IMonHoc daoMH = new MonHocDAO();


    @FXML
    private void initialize(){
        lopList = FXCollections.observableArrayList(dao.findAll());
        colSTT.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(lopList.indexOf(cellData.getValue()) + 1)));
        colMaLop.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));
        colTenLop.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenLop()));
        colTenGV.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenGiangVien()));
        colDiaDiem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaDiem()));
        colThoiGian.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianHoc()));
        colMaMH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));
        tblLop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Điền thông tin vào các TextField
                txtMaLop.setText(newValue.getMaLop());
                txtTenLop.setText(newValue.getTenLop());
                txtTenGiangVien.setText(String.valueOf(newValue.getTenGiangVien()));
                txtDiaDiem.setText(String.valueOf(newValue.getDiaDiem()));
                txtThoiGian.setText(String.valueOf(newValue.getThoiGianHoc()));
                comBoMaMH.setValue(String.valueOf(newValue.getMaMH()));
            }
        });
        // Đưa dữ liệu vào TableView
        tblLop.setItems(lopList);
    }
    @FXML
    public void themBtnCLick(MouseEvent mouseEvent) {
        String maLop = txtMaLop.getText();
        String tenLop = txtTenLop.getText();
        String tenGV = txtTenGiangVien.getText();
        String diaDiem = txtDiaDiem.getText();
        String thoiGian = txtThoiGian.getText();
        String maMH = comBoMaMH.getValue();
        Lop tmp = Lop.builder().maLop(maLop)
                .diaDiem(diaDiem).thoiGianHoc(thoiGian).tenLop(tenLop)
                .tenGiangVien(tenGV).maMH(maMH).build();
        dao.addLop(tmp);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Đã thêm lớp học thành công");

        // Hiển thị thông báo
        alert.showAndWait();
        initialize();
    }

    public void suaBtnClick(MouseEvent mouseEvent) {
        String maLop = txtMaLop.getText();
        String tenLop = txtTenLop.getText();
        String tenGV = txtTenGiangVien.getText();
        String diaDiem = txtDiaDiem.getText();
        String thoiGian = txtThoiGian.getText();
        String maMH = comBoMaMH.getValue();
        Lop tmp = Lop.builder().maLop(maLop)
                .diaDiem(diaDiem).thoiGianHoc(thoiGian).tenLop(tenLop)
                .tenGiangVien(tenGV).maMH(maMH).build();
        dao.updateLop(tmp);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Đã sửa lớp học thành công");

        // Hiển thị thông báo
        alert.showAndWait();
        initialize();
    }

    public void xoaBtnClick(MouseEvent mouseEvent) {
        String maLop = txtMaLop.getText();
        dao.deleteLop(maLop);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thông báo");
        alert.setContentText("Đã sửa lớp học thành công");
        initialize();
    }
}
