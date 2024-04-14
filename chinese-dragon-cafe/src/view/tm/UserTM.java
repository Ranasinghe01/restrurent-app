package view.tm;

import javafx.scene.control.Button;

public class UserTM {
    
    private String username;
    private String password;
    private String role;
    private Button btnDelete;
    
    public UserTM(String username, String password, String role, Button btnDelete) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.btnDelete = btnDelete;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
    
}
