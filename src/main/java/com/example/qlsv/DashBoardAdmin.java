package com.example.qlsv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class DashBoardAdmin {

    @FXML
    public Pane panelContainer;
    @FXML
    public Label lblProfile;
    private Stage stage;
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void openForm(FXMLLoader Loader){
        AnchorPane form = null;
        try {
            form = Loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(panelContainer != null){
            panelContainer.getChildren().clear();
            panelContainer.getChildren().add(form);
        }
    }
    @FXML
    public void manageInfor(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/QLTTAdmin.fxml"));
        openForm(qltt);
        System.out.println(lblProfile.getText());

    }
    @FXML
    public void manageSubject(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/QLMHAdmin.fxml"));
        openForm(qltt);
        System.out.println(lblProfile.getText());
    }
    @FXML
    public void manageClasses(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/QLLHAdmin.fxml"));
        openForm(qltt);
    }
    @FXML
    public void LogoutClick(MouseEvent mouseEvent) {
        Stage newStage = new Stage();
        FXMLLoader login = new FXMLLoader(getClass().getResource("/com/example/qlsv/Login.fxml"));
        try {
            Scene newScene = new Scene(login.load(), 526, 387);
            newStage.setScene(newScene);
            newStage.setTitle("Trang đăng nhập");
            newStage.setResizable(false);
            newStage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon/student_3.png")));
            newStage.show();
            stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void manageScore(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/QLBDAdmin.fxml"));
        openForm(qltt);
    }
    @FXML
    public void ManageStatisticalByClass(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/ThongKeAdmin.fxml"));
        openForm(qltt);
    }
}
