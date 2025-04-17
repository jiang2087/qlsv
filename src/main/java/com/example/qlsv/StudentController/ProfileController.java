package com.example.qlsv.StudentController;

import com.example.qlsv.DAO.IUserDAO;
import com.example.qlsv.DAO.Impl.UserDAO;
import com.example.qlsv.Global.SimSes;
import com.example.qlsv.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProfileController {
    @FXML
    private TextField txtMaSV;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtGioiTinh;
    @FXML
    private TextField txtNgaySinh;
    @FXML
    private TextField txtEmail;
    @FXML
    private ImageView imageProfile;
    IUserDAO dao = new UserDAO();
    @FXML
    private void initialize(){
        User user = dao.findByMaSV(SimSes.G_MaSV);
        txtHoTen.setText(user.getHoTen());
        txtMaSV.setText(user.getMaSV());
        txtSDT.setText(user.getSoDienThoai());
        txtDiaChi.setText(user.getDiaChi());
        txtGioiTinh.setText(user.getGioiTinh());
        txtNgaySinh.setText(user.getNgaySinh().toString());
        txtEmail.setText(user.getEmail());
        Image image;
        if(user.getHinhAnh() != null){
            image = new Image(user.getHinhAnh());
        }else{
            image = new Image(getClass().getResourceAsStream("/Icon/student_3.png"));
        }


        imageProfile.setImage(image);
    }
    public void changeBtnClick(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        Stage stage = (Stage) txtDiaChi.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Lấy đường dẫn file ảnh và hiển thị ảnh
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Đường dẫn ảnh: " + filePath);
            dao.updateAnh("file:///" + filePath, SimSes.G_MaSV);
            // Tạo đối tượng Image từ đường dẫn và cài đặt cho ImageView
            Image image = new Image("file:///" + filePath);  // Đảm bảo thêm prefix "file:///"
            imageProfile.setImage(image);
        } else {
            // Nếu người dùng không chọn file, hiển thị thông báo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có file nào được chọn.");
            alert.showAndWait();
        }
    }

    public void UpdateClick(MouseEvent mouseEvent) {

        try {
            String maSV = txtMaSV.getText();
            String hoTen = txtHoTen.getText();
            String SDT = txtSDT.getText();
            String diaChi = txtDiaChi.getText();
            String gioiTinh = txtGioiTinh.getText();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = formatter.parse(txtNgaySinh.getText());
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            java.sql.Date ngaySinh = sqlDate;
            String email = txtEmail.getText();
            User user = User.builder().maSV(maSV)
                    .hoTen(hoTen)
                    .soDienThoai(SDT)
                    .diaChi(diaChi)
                    .gioiTinh(gioiTinh)
                    .ngaySinh(ngaySinh)
                    .email(email).build();
            dao.updateSV(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Sửa thành công!");
            alert.showAndWait();
            initialize();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
