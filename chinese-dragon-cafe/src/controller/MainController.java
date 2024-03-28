package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/CustomerView.fxml"))));
        stage.setTitle("Customer Form");
        stage.show();
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ItemView.fxml"))));
        stage.setTitle("Item Form");
        stage.show();

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/OrderView.fxml"))));
        stage.setTitle("Order Form");
        stage.show();

    }

}
