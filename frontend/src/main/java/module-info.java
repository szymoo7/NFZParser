module org.gui.frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.gui.frontend to javafx.fxml;
    exports org.gui.frontend;
}