module com.example.programming_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
	requires transitive javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.programming_project to javafx.fxml;
    exports com.example.programming_project;
}
