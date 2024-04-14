package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private AnchorPane root;

    
    @FXML
    void AddNewUserOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserView.fxml"))));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
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

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml"))));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"))));
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }
}
