package dto;

public class LoginDTO {
    
    private String username;
    private String passwrd;
    
    public LoginDTO(String username, String passwrd) {
        this.username = username;
        this.passwrd = passwrd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    
}
