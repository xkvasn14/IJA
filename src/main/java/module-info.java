module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens window to javafx.fxml;
    exports window;
}