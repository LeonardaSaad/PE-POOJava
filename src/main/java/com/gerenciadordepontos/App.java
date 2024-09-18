package com.gerenciadordepontos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public TextField nameField;
    public TextField ageField;
    public TextField cityField;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gerenciadordepontos/form.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setTitle("Gerenciamento de Ponto");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void saveFormDataToDatabase(ActionEvent actionEvent) {
        String name = nameField.getText();
        String age = ageField.getText();
        String city = cityField.getText();
        
        try {
            // Load the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite")) {
                // Create the table if it doesn't exist
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, city TEXT)";
                try (PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL)) {
                    createTableStmt.execute();
                }

                // Insert data into the database
                String insertSQL = "INSERT INTO users (name, age, city) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                    insertStmt.setString(1, name);
                    insertStmt.setInt(2, Integer.parseInt(age));
                    insertStmt.setString(3, city);
                    insertStmt.executeUpdate();
                    
                    // Show success message
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Concluído");
                    alert.setHeaderText(null);
                    alert.setContentText("Ponto Registrado com Sucesso!");
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("alert-confirm-styles.css").toExternalForm());
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            // Show error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Mensagem de erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro ao salvar: " + ex.getMessage());
            alert.getDialogPane().getStylesheets().add(getClass().getResource("alert-error-styles.css").toExternalForm());
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            // Show error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Mensagem de erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Erro: " + ex.getMessage());
            alert.getDialogPane().getStylesheets().add(getClass().getResource("alert-error-styles.css").toExternalForm());
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}