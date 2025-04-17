package com.example.qlsv.Global;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SimSes {
    public static String G_MaSV;
    public static String G_TenSV;
    public static String G_maLop;
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image image = new Image("file:///E:\\Giang\\Messenger\\message.png");
        alertStage.getIcons().add(image);
        ImageView customIcon = new ImageView(image);
        customIcon.setFitWidth(48);
        customIcon.setFitHeight(48);
        alert.setGraphic(customIcon);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
