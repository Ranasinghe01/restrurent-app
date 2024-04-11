package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderHistoryDetailsController {
    
    @FXML
    private TableColumn<?, ?> itemCode;

    @FXML
    private TableColumn<?, ?> orderId;

    @FXML
    private TableColumn<?, ?> qty;

    @FXML
    private TableView<?> tblOrderHistoryDetails;

    @FXML
    private TableColumn<?, ?> unitPrice;

    
}
