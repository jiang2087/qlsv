package com.example.qlsv.AdminController.Component;

import com.example.qlsv.DAO.IDiemDAO;
import com.example.qlsv.DAO.IUserDAO;
import com.example.qlsv.DAO.Impl.DiemDAO;
import com.example.qlsv.DAO.Impl.UserDAO;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.Diem;
import com.example.qlsv.model.Lop;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.FloatStringConverter;
import java.util.List;

public class AddScoreStudent {

    @FXML
    Label lblClassId;
    @FXML
    TableView<Diem> tblScore;
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
    IDiemDAO dao = new DiemDAO();
    private ObservableList<Diem> listDiem;

    private String maLop;

    public void setLabel(String x){
        Platform.runLater(() -> {
            this.maLop = x;
            this.lblClassId.setText(x);
        });
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Float xepLoai(Float a, Float b, Float c, Float d){
        Float x = (float) (a * 0.15 + b * 0.15 + c * 0.2 + d * 0.5);
       return x;
    }
    @FXML
    private void initialize(){
        tblScore.setEditable(true);
        Platform.runLater(() -> {
            List<Diem> list = dao.findAllInClass(SimSes.G_maLop);
            listDiem = FXCollections.observableArrayList(list);
            colSTT.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(listDiem.indexOf(cellData.getValue()) + 1)));
            colMaSV.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaSV()));
            colDiemTX1.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemTX1() != null ? cellData.getValue().getDiemTX1() : 0f).asObject());
            colDiemCuoiKy.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemCuoiKy() != null ? cellData.getValue().getDiemCuoiKy() : 0f).asObject());
            colDiemGiuaKy.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemGiuaKy() != null ? cellData.getValue().getDiemGiuaKy() : 0f).asObject());
            colDiemTX2.setCellValueFactory(cellData ->
                    new SimpleFloatProperty(cellData.getValue().getDiemTX2() != null ? cellData.getValue().getDiemTX2() : 0f).asObject());
            colDiemTX1.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            colDiemTX1.setOnEditCommit(event -> {
                event.getRowValue().setDiemTX1(event.getNewValue());
            });
            colDiemTX2.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            colDiemTX2.setOnEditCommit(event -> {
                event.getRowValue().setDiemTX2(event.getNewValue());
            });
            colDiemCuoiKy.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            colDiemCuoiKy.setOnEditCommit(event -> {
                event.getRowValue().setDiemCuoiKy(event.getNewValue());
            });
            colDiemGiuaKy.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            colDiemGiuaKy.setOnEditCommit(event -> {
                event.getRowValue().setDiemGiuaKy(event.getNewValue());
            });

            tblScore.setItems(listDiem);
        });

    }
    public void addScoreBtn(MouseEvent mouseEvent) {
        ObservableList<Diem> diem = tblScore.getItems();

        try{
            for(int j = 0; j < diem.size(); j++){
                Diem x = diem.get(j);
                System.out.println(x);
                Float TX1 = x.getDiemTX1();
                Float TX2 =x.getDiemTX2();
                Float giuaKy = x.getDiemGiuaKy();
                Float cuoiKy = x.getDiemCuoiKy();
                String maSV = x.getMaSV();
                Float xepLoai = xepLoai(TX1, TX2, giuaKy, cuoiKy);
                Diem tmp = Diem.builder().diemTX1(TX1).diemTX2(TX2)
                        .diemGiuaKy(giuaKy).diemCuoiKy(cuoiKy)
                        .maSV(maSV).maLop(SimSes.G_maLop).diemTichLuy(xepLoai).build();
                dao.updateDiem(tmp);
            }
            SimSes.showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Đã sửa điểm thành công");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText("");
            alert.setContentText("bạn chưa điền tất cả điểm rồi!");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
        initialize();
    }
}
