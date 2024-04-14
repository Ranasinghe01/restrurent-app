package controller;

import java.util.ArrayList;

import bo.BoFactory;
import bo.custom.UserBo;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    UserBo bo;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        bo = BoFactory.getInstance().getBo(BoFactory.BoType.USER);

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {

            ArrayList<UserDTO> allUser = bo.getAllUser();

            for (UserDTO userDTO : allUser) {

                    UserDTO user = new UserDTO(userDTO.getUsername(),
                            userDTO.getPassword(),
                            userDTO.getRole());   
                        
                    if ((user.getUsername().equals(username)) && (user.getPassword().equals(password))) {

                        if (user.getRole().equals("Admin")) {
                            
                            Stage stage = (Stage) root.getScene().getWindow();
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashboardView.fxml"))));
                            stage.setResizable(false);
                            stage.show();
                            stage.centerOnScreen();
    
                        }else {
                            Stage stage = (Stage) root.getScene().getWindow();
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml"))));
                            stage.setResizable(false);
                            stage.show();
                            stage.centerOnScreen();
                        }        
                    }
            }

        } catch (Exception e) {
           new Alert(AlertType.ERROR, "Exception" + e.getMessage()).show();
        }
    }
}
