module com.gerenciadordepontos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.gerenciadordepontos to javafx.fxml;
    exports com.gerenciadordepontos;
}
