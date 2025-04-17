package com.example.qlsv.StudentController;

import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.DAO.Impl.LopDAO;
import com.example.qlsv.DashBoardAdmin;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.Lop;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ClassController {
    @FXML
    private ComboBox<String> comboMaLop;
    @FXML
    private TableView<Lop> tblLop;
    @FXML
    private TableColumn<Lop, String> colSTT;
    @FXML
    private TableColumn<Lop, String> colMaMH;
    @FXML
    private TableColumn<Lop, String> colMaLop;
    @FXML
    private TableColumn<Lop, String> colTenGV;
    @FXML
    private TableColumn<Lop, String> colThoiGian;
    @FXML
    private TableColumn<Lop, String> colPhongHoc;

    ILopDAO dao = new LopDAO();
    private ObservableList<Lop> lopList;

    @FXML
    private void initialize(){
        lopList = FXCollections.observableArrayList(dao.findByMaSV(SimSes.G_MaSV));
        colSTT.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(lopList.indexOf(cellData.getValue()) + 1)));
        colMaLop.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));
        colMaMH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaLop()));
        colTenGV.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenGiangVien()));
        colPhongHoc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaDiem()));
        colThoiGian.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianHoc()));
        colMaMH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));

        // Đưa dữ liệu vào TableView
        tblLop.setItems(lopList);
    }


    public void SearchByLopClick(MouseEvent mouseEvent) {

    }
}
