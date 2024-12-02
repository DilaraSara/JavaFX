package com.javafx.javafx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

   public class PrimaryController {
    @FXML
    private TextField kullaniciAdiField;

    @FXML
    private PasswordField sifreField;

    @FXML
    private Label hataLabel;

     @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = kullaniciAdiField.getText();
        String password = sifreField.getText();

        AuthService authService = new AuthService();
        boolean authenticated = authService.authenticate(username, password);

        if (authenticated) {
            // Kullanıcı doğrulandı, ikinci sayfaya geçiş yapabilirsiniz
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent secondaryPage = loader.load();
            SecondaryController secondaryController = loader.getController();
            secondaryController.initializeData(); // Verileri çekip görüntüleme işlemi

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(secondaryPage));
        } else {
            // Doğrulama başarısız
            hataLabel.setText("Oturum açma başarısız!");
        }
    }
}


