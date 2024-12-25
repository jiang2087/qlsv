package com.example.qlsv.AdminController;

import com.example.qlsv.model.Diem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class QLBDAdminController {
    @FXML
    private TableView<Diem> tblBD;
    @FXML
    private TableColumn<Diem, String> colSTT;
    @FXML
    private TableColumn<Diem, String> colMaLop;
    @FXML
    private TableColumn<Diem, String> colTenLop;
    @FXML
    private TableColumn<Diem, String> colDiaDIem;
    @FXML
    private TableColumn<Diem, String> colThoiGian;
    @FXML
    private TableColumn<Diem, String> colMaMH;
    @FXML
    private TableColumn<Diem, String> themDiemBtn;

    public void testBtnClick(MouseEvent mouseEvent) {

    }
}
