package controller;

import java.util.ArrayList;

import bo.BoFactory;
import bo.custom.OrderDetailBo;
import dto.OrderDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.OrderDetailsHistoryTM;

public class OrderHistoryDetailsController {
    
    @FXML
    private TableColumn<OrderDetailsHistoryTM, String> itemCode;

    @FXML
    private TableColumn<OrderDetailsHistoryTM, String> orderId;

    @FXML
    private TableColumn<OrderDetailsHistoryTM, Integer> qty;

    @FXML
    private TableView<OrderDetailsHistoryTM> tblOrderHistoryDetails;

    @FXML
    private TableColumn<OrderDetailsHistoryTM, Double> unitPrice;

    OrderDetailBo bo;

    
    public void initialize() {

        bo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER_DETAIL);

        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        itemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        getAllOrderDetailsHistory();

    }

    private void getAllOrderDetailsHistory() {

        try {

            ArrayList<OrderDetailDTO> allOrderdetails = bo.getAllOrderDetails();
            ObservableList<OrderDetailsHistoryTM> tmList = FXCollections.observableArrayList();

            for (OrderDetailDTO orderDetailsHistoryTM : allOrderdetails) {
                
                tmList.add(new OrderDetailsHistoryTM(
                    orderDetailsHistoryTM.getOrderID(), 
                    orderDetailsHistoryTM.getCode(),
                    orderDetailsHistoryTM.getQty(),
                    orderDetailsHistoryTM.getUnitPrice()
                    ));
            }
            tblOrderHistoryDetails.setItems(tmList);

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, "SQL Exception" + e.getMessage());
            alert.show();
        }
    }
}
