package com.example.qlsv.AdminController;

import com.example.qlsv.AdminController.Component.TKStudent;
import com.example.qlsv.DAO.ILopDAO;
import com.example.qlsv.DAO.Impl.LopDAO;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.Lop;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class QLTKController {

    @FXML
    private TableView<Lop> tblLop;
    @FXML
    private TableColumn<Lop, String> colSTT;
    @FXML
    private TableColumn<Lop, String> colMaLop;
    @FXML
    private TableColumn<Lop, String> colThoiGian;
    @FXML
    private TableColumn<Lop, String> colTenLop;
    @FXML
    private TableColumn<Lop, String> colDiaDiem;
    @FXML
    private TableColumn<Lop, Void> colTK;
    @FXML
    private TableColumn<Lop, String> colMaMH;

    private ObservableList<Lop> lopList;
    private ILopDAO dao = new LopDAO();
    @FXML
    private void initialize(){
        lopList = FXCollections.observableArrayList(dao.findAll());
        colSTT.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(lopList.indexOf(cellData.getValue()) + 1)));
        colMaLop.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaLop()));
        colTenLop.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTenLop()));
        colDiaDiem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDiaDiem()));
        colMaMH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaMH()));
        colThoiGian.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianHoc()));
        // Đưa dữ liệu vào TableView

        colTK.setCellFactory(column -> {
            return new TableCell<Lop, Void>() {
                private final Button btn = new Button("Xem thống kê");
                {
                    tblLop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                            Lop selectedLop = (Lop) newValue;
                            btn.setOnAction(event -> {
                                try {
                                    // Tạo cửa sổ mới
                                    Stage newStage = new Stage();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/ComponentAdmin/TKStudent.fxml"));
                                    Scene newScene = new Scene(loader.load(), 802, 500);

                                    // Lấy controller và truyền dữ liệu
                                    TKStudent controller = loader.getController();
                                    controller.setLabel(selectedLop.getMaLop());
                                    SimSes.G_maLop = selectedLop.getMaLop();

                                    // Thiết lập và hiển thị cửa sổ
                                    newStage.setScene(newScene);
                                    newStage.setTitle("Trang xem thống kê");
                                    newStage.setResizable(false);
                                    newStage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon/student_3.png")));
                                    newStage.show();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                    });

                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null); // Nếu ô rỗng, không hiển thị nút
                    } else {
                        setGraphic(btn); // Hiển thị nút trong ô
                    }
                }
            };
        });
        tblLop.setItems(lopList);
    }

}
