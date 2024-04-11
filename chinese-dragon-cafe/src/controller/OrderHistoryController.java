package controller;

import java.util.ArrayList;
import java.util.Date;

import bo.BoFactory;
import bo.custom.OrderBo;
import dto.OrderDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.OrderHistoryTM;

public class OrderHistoryController{

    @FXML
    private TableColumn<OrderHistoryTM, String> custID;

    @FXML
    private TableColumn<OrderHistoryTM, Date> orderDate;

    @FXML
    private TableColumn<OrderHistoryTM, String> orderID;

    @FXML
    private TableView<OrderHistoryTM> tblOrderHistory;

    OrderBo bo;

    public void initialize() {

        bo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER);

        orderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        custID.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        getOrders();
    }

    public void getOrders() {

        try {
            ArrayList<OrderDTO> allOrders = bo.getAllOrder();
            ObservableList<OrderHistoryTM> tmList = FXCollections.observableArrayList();

            for (OrderDTO order : allOrders) {
                
                OrderHistoryTM orderHistoryTM = new OrderHistoryTM(
                    order.getOrderID(),
                    order.getOrderDate(),
                    order.getCustomerID()
                );
                tmList.add(orderHistoryTM);
            }
            tblOrderHistory.setItems(tmList);

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, "SQL Exception" + e.getMessage());
            alert.show();
        }
    }
}