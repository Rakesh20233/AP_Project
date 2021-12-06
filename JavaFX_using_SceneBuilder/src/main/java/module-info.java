module com.example.javafx_using_scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_using_scenebuilder to javafx.fxml;
    exports com.example.javafx_using_scenebuilder;
}