package com.example.qlsv;

import com.example.qlsv.DAO.Impl.UserDAO;
import com.example.qlsv.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    private final UserDAO userDAO = new UserDAO(); // Tạo đối tượng DAO

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            // Gọi DAO để kiểm tra thông tin đăng nhập
            User user = userDAO.login(username, password);

            if (user != null) {
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng nhập thành công!");
                if(!user.isQuyen()) {
                    navigateToAdminPage();
                } else  {
                    navigateToStudentPage();
                }
                // Logic khi đăng nhập thành công (chuyển sang màn hình chính)
//                navigateToHomePage(user);
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Đã xảy ra lỗi khi đăng nhập. Vui lòng thử lại!");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void navigateToAdminPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/qlsv/Admin/DashBoardAdmin.fxml"));
            Parent root = loader.load();

//            // Truyền dữ liệu người dùng sang controller của Home
//            DashBoardAdmin controller = loader.getController();
//            controller.setCurrentUser(user);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void navigateToStudentPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/qlsv/Student/DashBoardSV.fxml"));
            Parent root = loader.load();

//            // Truyền dữ liệu người dùng sang controller của Home
//            DashBoardAdmin controller = loader.getController();
//            controller.setCurrentUser(user);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
