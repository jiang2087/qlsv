module com.example.qlsv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.net.http;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires lombok;
    opens com.example.qlsv.AdminController to javafx.fxml;
    exports com.example.qlsv.AdminController;
    opens com.example.qlsv to javafx.fxml;
    exports com.example.qlsv;
    opens com.example.qlsv.AdminController.Component to javafx.fxml;
    exports com.example.qlsv.AdminController.Component;
    exports com.example.qlsv.model;

}