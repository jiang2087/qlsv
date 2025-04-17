package com.example.qlsv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardSV {
    @FXML
    private Label lblProfile;
    @FXML
    private Label lblClasses;
    @FXML
    private Label lblScoreResult;
    @FXML
    private Label lblLogout;
    @FXML
    private Stage stage;
    public void setStage(Stage stage){
        this.stage = stage;
    }
    @FXML
    private Pane panelContainer;
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
    protected void profileClick(MouseEvent mouseEvent){
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Student/ProfileSV.fxml"));
        openForm(qltt);
   }

    public void scoreResultClick(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Student/ScoreSV.fxml"));
        openForm(qltt);
    }

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
            Stage stage = (Stage) lblLogout.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClassesClick(MouseEvent mouseEvent) {
        FXMLLoader qltt = new FXMLLoader(getClass().getResource("/com/example/qlsv/Student/ClassSV.fxml"));
        openForm(qltt);
    }
}

