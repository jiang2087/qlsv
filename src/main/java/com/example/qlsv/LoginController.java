package com.example.qlsv;

import com.example.qlsv.DAO.Impl.UserDAO;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    private final UserDAO userDAO = new UserDAO(); // Tạo đối tượng DAO

    @FXML
    private CheckBox checkBox;
    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        passwordField.setPromptText("Nhập mật khẩu của bạn");
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            SimSes.showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            // Gọi DAO để kiểm tra thông tin đăng nhập
            User user = userDAO.login(username, password);

            if (user != null) {
                SimSes.G_TenSV = user.getHoTen();
                SimSes.G_MaSV = user.getMaSV();
                SimSes.showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng nhập thành công!");
                if(!user.isQuyen()) {
                    navigateToAdminPage();
                } else  {
                    navigateToStudentPage();
                }
                // Logic khi đăng nhập thành công (chuyển sang màn hình chính)
//                navigateToHomePage(user);
            } else {
                SimSes.showAlert(Alert.AlertType.ERROR, "Lỗi", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            SimSes.showAlert(Alert.AlertType.ERROR, "Lỗi", "Đã xảy ra lỗi khi đăng nhập. Vui lòng thử lại!");
        }
    }

    private void navigateToAdminPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/DashBoardAdmin.fxml"));
            Parent root = loader.load();

            DashBoardAdmin controller = loader.getController();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            controller.setStage(stage);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void navigateToStudentPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/qlsv/Student/DashBoardSV.fxml"));
            Parent root = loader.load();

            DashBoardSV controller = loader.getController();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            controller.setStage(stage);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void check(MouseEvent mouseEvent) {
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                passwordField.setStyle("-fx-text-fill: black;");  // Tùy chọn màu chữ, không cần nếu không muốn thay đổi
                passwordField.setPromptText(passwordField.getText());
                passwordField.setText(passwordField.getText()); // G

            } else {
                // Nếu CheckBox không được chọn, thay thế ký tự mật khẩu bằng dấu chấm
                passwordField.setStyle("-fx-text-fill: black;"); // Tùy chọn màu chữ

            }
        });
    }
}