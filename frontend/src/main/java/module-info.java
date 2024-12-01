module org.gui.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires backend;
    requires jdk.jdi;


    opens org.gui.frontend to javafx.fxml;
    exports org.gui.frontend;
}