package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminDashboardController {
    
    @FXML
    void AddNewUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderDetailsHistoryOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderHistoryDetailsView.fxml"))));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void btnOrderHistoryOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderHistoryView.fxml"))));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }
}
