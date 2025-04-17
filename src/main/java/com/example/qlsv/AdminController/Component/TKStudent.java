package com.example.qlsv.AdminController.Component;

import com.example.qlsv.DAO.IDiemDAO;
import com.example.qlsv.DAO.Impl.DiemDAO;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.Diem;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;

import java.util.List;

public class TKStudent {

    @FXML
    Label lblClassId;
    @FXML
    TableView<Diem> tblTK;
    @FXML
    TableColumn<Diem, String> colSTT;
    @FXML
    TableColumn<Diem, String> colMaSV;
    @FXML
    TableColumn<Diem, Float> colDiemTX1;
    @FXML
    TableColumn<Diem, Float> colDiemTX2;
    @FXML
    TableColumn<Diem, Float> colDiemGiuaKy;
    @FXML
    TableColumn<Diem, Float> colDiemCuoiKy;
    @FXML
    TableColumn<Diem, String> colTK;

    IDiemDAO dao = new DiemDAO();
    private ObservableList<Diem> listDiem;
    private String maLop;

    public void setLabel(String x){
        Platform.runLater(() -> {
            this.maLop = x;
            this.lblClassId.setText(x);
        });
    }

    private String xepLoai(Float a, Float b, Float c, Float d){
        Float x = (float) (a * 0.15 + b * 0.15 + c * 0.2 + d * 0.5);
        float epsilon = (float)1e-6;
        if(x > 1 - epsilon){
            return "Đạt";
        }else{
            return "Trượt";
        }

    }
    @FXML
    private void initialize(){
        Platform.runLater(() -> {
            List<Diem> list = dao.findAllInClass(SimSes.G_maLop);
            listDiem = FXCollections.observableArrayList(list);
            colSTT.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(listDiem.indexOf(cellData.getValue()) + 1)));
            colMaSV.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaSV()));
            colDiemTX1.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getDiemTX1()).asObject());
            colDiemCuoiKy.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getDiemCuoiKy()).asObject());
            colDiemGiuaKy.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getDiemGiuaKy()).asObject());
            colDiemTX2.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getDiemTX2()).asObject());
            colTK.setCellValueFactory(cellData -> {
                Diem diem = cellData.getValue();

                // Tính toán giá trị tổng kết từ các cột (diemTX1, diemTX2, diemGiuaKy, diemCuoiKy)
                float diemTX1 = diem.getDiemTX1();
                float diemTX2 = diem.getDiemTX2();
                float diemGiuaKy = diem.getDiemGiuaKy();
                float diemCuoiKy = diem.getDiemCuoiKy();

                String xepLoai = xepLoai(diemTX1, diemTX2, diemGiuaKy, diemCuoiKy); // Hàm này sẽ trả về xếp loại dựa trên điểm tổng kết

                return new SimpleStringProperty(xepLoai); // Trả về giá trị xếp loại cho cột TK
            });
            tblTK.setItems(listDiem);
        });

    }

}
