package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.Validator;
import view.tm.CustomerTM;

public class CustomerController {

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private VBox vBox;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CustomerTM, String> custContact;

    @FXML
    private TableColumn<CustomerTM, String> custID;

    @FXML
    private TableColumn<CustomerTM, String> custName;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<CustomerTM, Button> custDelete;

    CustomerBo bo;

    ObservableList<CustomerTM> tmList = FXCollections.observableArrayList(); 
    
    private void enableUI(boolean enable) {
        vBox.setDisable(!enable);
        btnSave.setDisable(!enable);
        btnUpdate.setDisable(!enable);
    }

    private String generateNewCustomerID() {
        if(tmList.isEmpty()) return "C001";

        String newID = tmList.get(tmList.size() - 1).getId()
                    .replace("C", "") + 1;
        return "C%03d".formatted(newID);
    }

    public void initialize() {

        bo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);

        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custName.setCellValueFactory(new PropertyValueFactory<>("name"));
        custContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        getCustomers();
        enableUI(false);
    }

    public void getCustomers() {

        try {
            ArrayList<CustomerDTO> allCustomer = bo.getAllCustomer();          

            for (CustomerDTO customer : allCustomer) {
                Button btnDelete = new Button("Delete");
                btnDelete.setMaxSize(100, 50);
                btnDelete.setCursor(Cursor.HAND);
                btnDelete.setStyle("-fx-background-color : #c0392b");
                btnDelete.setTextFill(Color.web("#ecf0f1"));

                CustomerTM customerTM = new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getContact(),
                        btnDelete);

                tmList.add(customerTM);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ! ", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();

                    try {
                        if (result.orElse(no) == ok) {

                            if (bo.deleteCustomer(customerTM.getId())) {

                                new Alert(AlertType.CONFIRMATION, "Customer is Deleted ! ").show();
                                getCustomers();
                                return;
                            }
                        }

                    } catch (Exception e1) {
                        Alert alert1 = new Alert(AlertType.ERROR, "SQL Exception" + e1.getMessage());
                        alert1.showAndWait();
                    }
                });
            }

            tblCustomer.setItems(tmList);

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, "SQL Exception" + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnNewCustomerOnAction() {
        enableUI(true);
        txtCustomerID.setText(generateNewCustomerID());
        txtCustomerName.requestFocus();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {

        boolean validate = true;

        if (txtContact.getText().isEmpty()) {
            txtContact.setStyle("-fx-border-color:red");
            txtContact.requestFocus();
            validate = false;
        }
        if(txtCustomerName.getText().isEmpty()){
            txtCustomerName.setStyle("-fx-border-color:red");
            txtContact.requestFocus();
            validate = false;
        }
        if (!validate) return; 

        try {
            // get inserted data from the text fields
            String id = txtCustomerID.getText();
            String name = txtCustomerName.getText();
            int contact = Integer.parseInt(txtContact.getText());

            CustomerDTO customerDTO = new CustomerDTO(id, name, contact);

            boolean isSaved = bo.saveCustomer(customerDTO);

            if (isSaved) {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Customer is Saved");
                alert.show();
                getCustomers();

            } else {
                Alert alert = new Alert(AlertType.ERROR, "Customer is not Saved");
                alert.show();
            }

        } catch (SQLException sqlExceptionexception) {
            Alert alert = new Alert(AlertType.ERROR, "SQL Exception" + sqlExceptionexception.getMessage());
            alert.showAndWait();
        }

    }

    // load data using click event

    // @FXML
    // void btnViewOnAction(ActionEvent event) throws SQLException {

    // // define the query

    // PreparedStatement prepareStatement =
    // DbConnection.getInstance().getConnection()
    // .prepareStatement("SELECT * FROM Customer");

    // get the result set

    // ResultSet customerSet = prepareStatement.executeQuery();

    // ArrayList<Customer> customerList = new ArrayList<Customer>();

    // //add customer objects to the Array list

    // while (customerSet.next()) {
    // Customer customer = new Customer(
    // customerSet.getInt(1),
    // customerSet.getString(2),
    // customerSet.getString(3),
    // customerSet.getDouble(4));

    // customerList.add(customer);
    // }

    // create observableList to add customer table model
    // ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();

    // add from Array List to observableList

    // for (Customer customer : customerList) {
    // CustomerTM customerTM = new CustomerTM(
    // customer.getId(),
    // customer.getName(),
    // customer.getAddress(),
    // customer.getSalary());

    // tmList.add(customerTM);
    // }
    // set observerlist to tabel
    // tblCustomer.setItems(tmList);

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {

        boolean validate = true;

        if (txtContact.getText().isEmpty()) {
            txtContact.setStyle("-fx-border-color:red");
            txtContact.requestFocus();
            validate = false;
        }
        if(txtCustomerName.getText().isEmpty()){
            txtCustomerName.setStyle("-fx-border-color:red");
            txtContact.requestFocus();
            validate = false;
        }
        if (!validate) return; 

        try {
            // get inserted data from the text fields
            String id = txtCustomerID.getText();
            String name = txtCustomerName.getText();
            int contact = Integer.parseInt(txtContact.getText());

            CustomerDTO customerDTO = new CustomerDTO(id, name, contact);

            boolean isUpdated = bo.updateCustomer(customerDTO);

            if (isUpdated) {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Customer is Updated");
                alert.show();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Customer is not UPdated");
                alert.show();
            }

        } catch (SQLException sqlExceptionexception) {
            Alert alert = new Alert(AlertType.ERROR, "SQL Exception" + sqlExceptionexception.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    void txtCustomerIDOnAction(ActionEvent event) {

        try {
            String id = txtCustomerID.getText();
            CustomerDTO dto = bo.getCustomer(id);

            if (dto != null) {

                txtCustomerName.setText(dto.getName());
                txtContact.setText(String.valueOf(dto.getContact()));

            } else {
                new Alert(AlertType.ERROR, "Customer Not Found, Please check the customer ID and try again !").show();
            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR, "SQL Exception" + e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    void txtIdOnKeyReleased(KeyEvent event) {

        if (Validator.validateTextField(txtCustomerID, "^[0-9]{1,}$")) {
            txtCustomerID.setStyle("-fx-focus-color:green");

        } else {
            txtCustomerID.setStyle("-fx-focus-color:red");
        }
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {

        if (Validator.validateTextField(txtCustomerName, "^[A-z| ]{1,}$")
                && (!(txtCustomerName.getText().length() < 3))) {
            txtCustomerName.setStyle("-fx-focus-color:green");

        } else {
            txtCustomerName.setStyle("-fx-focus-color:red");
        }
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {

        if (Validator.validateTextField(txtContact, "^[0-9]{1,}$") 
                && (!(txtContact.getText().length() < 9))) {
            txtContact.setStyle("-fx-focus-color:green");

        } else {
            txtContact.setStyle("-fx-focus-color:red");
        }

    }

}
