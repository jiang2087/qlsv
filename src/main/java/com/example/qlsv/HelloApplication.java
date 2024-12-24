package com.example.qlsv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/qlsv/Admin/DashBoardAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Ứng dụng quản lý sinh viên");
        stage.setScene(scene);
        DashBoardAdmin admin = fxmlLoader.getController();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon/student_3.png")));
        admin.setStage(stage);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}