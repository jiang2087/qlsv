package com.example.qlsv.AdminController;

import com.example.qlsv.DAO.IMonHoc;
import com.example.qlsv.DAO.Impl.MonHocDAO;
import com.example.qlsv.model.MonHoc;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class QLMHAdminController {
    @FXML
    private TextField maMHField, tenMHField, soTinChiField, tinChiTHField, tinChiLTField, tinChiBTLField, hocKyField;

    @FXML
    private Button themButton;

    @FXML
    private TableView<MonHoc> monHocTable;

    @FXML
    private TableColumn<MonHoc, String> sttColumn;
    @FXML
    private TableColumn<MonHoc, String> maMHColumn;
    @FXML
    private TableColumn<MonHoc, String> tenMonHocColumn;
    @FXML
    private TableColumn<MonHoc, Integer> soTinChiColumn;
    @FXML
    private TableColumn<MonHoc, Integer> tinChiLTColumn;
    @FXML
    private TableColumn<MonHoc, Integer> tinChiTHColumn;
    @FXML
    private TableColumn<MonHoc, Integer> tinChiBTLColumn;
    @FXML
    private TableColumn<MonHoc, Integer> hocKyColumn;
    @FXML
    private Button suaButton;
    @FXML
    private Button xoaButton;

    private ObservableList<MonHoc> monHocList;


    @FXML
    private void initialize() {
        // Gọi phương thức findAll để lấy dữ liệu từ cơ sở dữ liệu
        IMonHoc dao = new MonHocDAO();
        monHocList = FXCollections.observableArrayList(dao.findAll());

        // Cài đặt các cột trong TableView
        sttColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(monHocList.indexOf(cellData.getValue()) + 1)));
        maMHColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));
        tenMonHocColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenMonHoc()));
        soTinChiColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoTinChi()).asObject());
        tinChiLTColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTinChiLT()).asObject());
        tinChiTHColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTinChiTH()).asObject());
        tinChiBTLColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTinChiBTL()).asObject());
        hocKyColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHocKy()).asObject());


        // Khi chọn một dòng trong TableView, điền dữ liệu vào các TextField
        monHocTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Điền thông tin vào các TextField
                maMHField.setText(newValue.getMaMH());
                tenMHField.setText(newValue.getTenMonHoc());
                soTinChiField.setText(String.valueOf(newValue.getSoTinChi()));
                tinChiLTField.setText(String.valueOf(newValue.getTinChiLT()));
                tinChiTHField.setText(String.valueOf(newValue.getTinChiTH()));
                tinChiBTLField.setText(String.valueOf(newValue.getTinChiBTL()));
                hocKyField.setText(String.valueOf(newValue.getHocKy()));
            }
        });
        // Đưa dữ liệu vào TableView
        monHocTable.setItems(monHocList);


        themButton.setOnAction(event -> themMonHoc());
        suaButton.setOnAction(event -> suaMonHoc());
        xoaButton.setOnAction(event -> xoaMH());
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void themMonHoc() {
        String maMH = maMHField.getText();
        String tenMH = tenMHField.getText();
        int soTinChi = Integer.parseInt(soTinChiField.getText());
        int tinChiTH = Integer.parseInt(tinChiTHField.getText());
        int tinChiLT = Integer.parseInt(tinChiLTField.getText());
        int tinChiBTL = Integer.parseInt(tinChiBTLField.getText());
        int hocKy = Integer.parseInt(hocKyField.getText());

        MonHoc monHoc = MonHoc.builder()
                .maMH(maMH)
                .tenMonHoc(tenMH)
                .soTinChi(soTinChi)
                .tinChiTH(tinChiTH)
                .tinChiLT(tinChiLT)
                .tinChiBTL(tinChiBTL)
                .hocKy(hocKy)
                .build();

        MonHocDAO dao = new MonHocDAO();
        dao.addMH(monHoc); // Gọi phương thức thêm môn học
        // Làm mới danh sách monHocList từ cơ sở dữ liệu
        monHocList.clear();
        monHocList.addAll(dao.findAll()); // Cập nhật lại danh sách
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm môn học thành công!");
        // Cập nhật lại TableView để hiển thị dữ liệu mới
        monHocTable.setItems(monHocList);

    }

    private void suaMonHoc() {
        // Lấy dữ liệu từ các trường nhập liệu
        String maMH = maMHField.getText();
        String tenMH = tenMHField.getText();
        int soTinChi = Integer.parseInt(soTinChiField.getText());
        int tinChiTH = Integer.parseInt(tinChiTHField.getText());
        int tinChiLT = Integer.parseInt(tinChiLTField.getText());
        int tinChiBTL = Integer.parseInt(tinChiBTLField.getText());
        int hocKy = Integer.parseInt(hocKyField.getText());

        // Tạo đối tượng MonHoc mới từ dữ liệu nhập vào
        MonHoc monHoc = MonHoc.builder()
                .maMH(maMH)
                .tenMonHoc(tenMH)
                .soTinChi(soTinChi)
                .tinChiTH(tinChiTH)
                .tinChiLT(tinChiLT)
                .tinChiBTL(tinChiBTL)
                .hocKy(hocKy)
                .build();
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "sửa môn học thành công!");
        // Cập nhật môn học trong cơ sở dữ liệu
        MonHocDAO dao = new MonHocDAO();
        dao.updateMH(monHoc); // Giả sử bạn có phương thức updateMH để cập nhật môn học trong cơ sở dữ liệu

        // Làm mới danh sách monHocList và cập nhật lại TableView
        monHocList.clear();
        monHocList.addAll(dao.findAll());
        monHocTable.setItems(monHocList);
    }
    private void xoaMH(){
        String maMH = maMHField.getText();
        MonHocDAO dao = new MonHocDAO();
        dao.deleteMH(maMH);
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa môn học thành công!");
        monHocList.clear();
        monHocList.addAll(dao.findAll());
        monHocTable.setItems(monHocList);
    }
}
