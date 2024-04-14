package controller;

import java.util.ArrayList;
import java.util.Optional;

import bo.BoFactory;
import bo.custom.UserBo;
import dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.tm.UserTM;

public class UserController {
    
    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ToggleGroup grpRole;

    @FXML
    private TableColumn<UserTM, String> colPassword;

    @FXML
    private TableColumn<UserTM, Button> colDelete;

    @FXML
    private RadioButton rdAdmin;

    @FXML
    private RadioButton rdUser;

    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TableColumn<UserTM, String> colUsername;

    @FXML
    private VBox vBox;

    UserBo bo;


    public void initialize() {

        enableUI(false);

        bo = BoFactory.getInstance().getBo(BoFactory.BoType.USER);

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        
        getUsers();
    }

    private void enableUI(boolean enable) {
        vBox.setDisable(!enable);
        btnSave.setDisable(!enable);
        btnUpdate.setDisable(!enable);
    }

    @FXML
    void btnNewUserOnAction(ActionEvent event) {
        enableUI(true);
    }

    private void getUsers() {

        try {

            ArrayList<UserDTO> allUsers = bo.getAllUser();
            ObservableList<UserTM> tmList = FXCollections.observableArrayList();

            for (UserDTO user : allUsers) {
                Button btnDelete = new Button("Delete");
                btnDelete.setMaxSize(100, 50);
                btnDelete.setCursor(Cursor.HAND);
                btnDelete.setStyle("-fx-background-color : #c0392b");
                btnDelete.setTextFill(Color.web("#ecf0f1"));

                UserTM userTM = new UserTM(
                    user.getUsername(), 
                    user.getPassword(), 
                    user.getRole(), btnDelete);

                tmList.add(userTM);

                btnDelete.setOnAction((e)-> {
                    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure ! ", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();

                    try {
                        
                        if(result.orElse(no) == ok) {

                            if(bo.deleteUser(userTM.getUsername())) {

                                Alert alertDeleted = new Alert(AlertType.CONFIRMATION, "User is Deleted");
                                ButtonType ok2 = new ButtonType("OK", ButtonData.OK_DONE);
                                alertDeleted.getButtonTypes().setAll(ok2);

                                Optional<ButtonType> result2 = alertDeleted.showAndWait();
                                if(result2.isPresent() && result2.get() == ok2) {

                                    getUsers();
                                    return;
                                } 
                            }
                        }

                    } catch (Exception e1) {
                        new Alert(AlertType.ERROR, "Exception" + e1.getMessage()).show();
                    }
                });
            }

            tblUser.setItems(tmList);

        } catch (Exception e2) {
            new Alert(AlertType.ERROR, "Exception" + e2.getMessage()).show();
        }
    }

    private boolean validate() {
        
        boolean validate = true;

        for (Node node : vBox.lookupAll(".error")) {
            node.getStyleClass().remove("error");
        }

        if (grpRole.getSelectedToggle() == null) {
            rdAdmin.getStyleClass().add("error");
            rdUser.getStyleClass().add("error");
            rdAdmin.requestFocus();
            validate = false;
        }

        String username = txtUsername.getText();
        if (username.isEmpty()) {
            txtUsername.getStyleClass().add("error");
            txtUsername.requestFocus();
            validate = false;
        }

        String password = txtPassword.getText();
        if (password.isEmpty()) {
            txtPassword.getStyleClass().add("error");
            txtPassword.requestFocus();
            validate = false;
        }

        if (validate) {
            return validate;
        }

        return false;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    if (validate()) {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            
            UserDTO userDTO = new UserDTO(username,
                    password, 
                    ((RadioButton) grpRole.getSelectedToggle()).getText()
                    );

                boolean isSaved = bo.saveUser(userDTO);
        
                if (isSaved) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "User is Saved");
                    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(ok);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ok) {

                        getUsers();

                    for (TextField textField : new TextField[] {txtUsername, txtPassword}) {
                        textField.clear();
                    }

                    rdAdmin = null;
                    rdUser = null;

                    enableUI(false);

                    }

                } else {
                    Alert alert = new Alert(AlertType.ERROR, "User is not Saved");
                    alert.show();
                }

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR, "Exception" + e.getMessage());
                alert.show();
            }
        }
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

        try {

            String username = txtUsername.getText();
            UserDTO dto = bo.getUser(username);

            if (dto != null) {
                txtUsername.setText(dto.getUsername());
                txtPassword.setText(dto.getPassword());

            }else {
                new Alert(AlertType.ERROR, "User Not Found, Please check the Username and try again !").show();
            }

        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Exception" + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if (validate()) {

            try {

                String username = txtUsername.getText();
                String password = txtPassword.getText();

                UserDTO userDTO = new UserDTO(username,
                        password,
                        ((RadioButton) grpRole.getSelectedToggle()).getText()
                        );

                boolean isUpdated = bo.updateUser(userDTO);

                if (isUpdated) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "User is Updated !");
                    ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(ok);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ok) {
                
                        getUsers();

                        for (TextField textField : new TextField[] {txtUsername, txtPassword}) {
                            textField.clear();
                        }

                        rdAdmin = null;
                        rdUser = null;

                    }

                }else {
                    Alert alert = new Alert(AlertType.ERROR, "User is not Updated !");
                    alert.show();
                }

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR,"Exception" + e.getMessage());
                alert.show();
            }
        }
    }   
}
