package com.javafx.javafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    private Label dataLabel;
    // TilePane bileşeni ekledik
 /*@FXML
    public void initialize() {
        // Bu metot, Controller başlatıldığında çalışır
        // Veri alma işlemini bu metot içinde gerçekleştirebilirsiniz
        initializeData();
    }*/
/*@FXML
    public void initializeData() {
        
        try {
            String serverUrl = "http://10.0.60.30:2700/drk15";
            String username = "MESSTAJER";
            String password = "12345";

            String responseData = fetchDataFromServer(serverUrl, username, password);

            if (responseData != null) {
                // Verileri Label içine ekleyin
                dataLabel.setText("Veriler: " + responseData);
            } else {
                dataLabel.setText("Verileri almak başarısız oldu.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            dataLabel.setText("Verileri almak başarısız oldu.");
        }
        
    }*/
    @FXML
    private void closeWindow(ActionEvent event) {
        // Kapat düğmesine tıklandığında pencereyi kapatın
        Stage stage = (Stage)dataLabel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public static String fetchDataFromServer(String serverUrl, String username, String password ) throws IOException {
                try {
                        URL url = new URL(serverUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);

                        int responseCode = connection.getResponseCode();

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String line;
                                StringBuilder response = new StringBuilder();

                                while ((line = reader.readLine()) != null) {
                                        response.append(line);
                                }
                                reader.close();

                                connection.disconnect();

                                return response.toString();
                        } else {
                                return null;
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        
    @FXML
    public void initializeData() throws IOException {
        try {
            String serverUrl = "http://10.0.60.30:2700/drk15/BIOnlineKPI/56";
            String username = "MESSTAJER";
            String password = "12345";
            String AuthService = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            String responseData = fetchDataFromServer(serverUrl, username, password);

            if (responseData != null) {
              
                dataLabel.setText("Veriler: " + responseData);
            } else {
                dataLabel.setText("Verileri almak başarısız oldu.");
            }
         
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + AuthService);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\n"); // Her satırı yeni satır ile birlikte ekleyin
                    }
                }

                // Verileri TextArea içine ekleyin
                dataLabel.setText(response.toString());
            } else {
                dataLabel.setText("Verileri almak başarısız oldu. Yanıt kodu: " + responseCode);
            }
            connection.disconnect();
         }catch (IOException e) {
            e.printStackTrace();

        }
}

    }

    
    

   
    
    
