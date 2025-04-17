package com.example.qlsv.StudentController;

import com.example.qlsv.DAO.IDiemDAO;
import com.example.qlsv.DAO.Impl.DiemDAO;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.Diem;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ScoreController {
    @FXML
    private TableView<Diem> tblTK;
    @FXML
    private TableColumn<Diem, String> colSTT;
    @FXML
    private TableColumn<Diem, String> colMaMH;
    @FXML
    private TableColumn<Diem, String> colMaLop;
    @FXML
    private TableColumn<Diem, Float> colTX1;
    @FXML
    private TableColumn<Diem, Float> colTX2;
    @FXML
    private TableColumn<Diem, Float> colGiuaKy;
    @FXML
    private TableColumn<Diem, Float> colCuoiKy;
    @FXML
    private TableColumn<Diem, Float> colTichLuy;

    private ObservableList<Diem> listDiem;
    IDiemDAO dao = new DiemDAO();

    @FXML
    private void initialize(){
        List<Diem> list = dao.findByMaSV(SimSes.G_MaSV);
        System.out.println(list);
        if(list.getFirst() != null){
            listDiem = FXCollections.observableArrayList(list);
            colSTT.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(listDiem.indexOf(cellData.getValue()) + 1)));
            colMaLop.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaLop()));
            colMaMH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));
            colTX1.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemTX1() != null ? cellData.getValue().getDiemTX1() : 0f).asObject());
            colTX2.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemTX2()!= null ? cellData.getValue().getDiemTX2() : 0f ).asObject());
            colGiuaKy.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemGiuaKy() != null ? cellData.getValue().getDiemGiuaKy() : 0f).asObject());
            colCuoiKy.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemCuoiKy() != null ? cellData.getValue().getDiemCuoiKy() : 0f).asObject());
            colTichLuy.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemTichLuy() != null ? cellData.getValue().getDiemTichLuy() : 0f).asObject());
            tblTK.setItems(listDiem);
        }else{
            SimSes.showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Sinh viên chưa có điểm");
        }
        // Đưa dữ liệu vào TableView

    }
}
