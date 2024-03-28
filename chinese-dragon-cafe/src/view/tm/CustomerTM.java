package view.tm;

import javafx.scene.control.Button;

public class CustomerTM {

    private String id;
    private String name;
    private int contact;
    private Button btnDelete;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, int contact, Button btnDelete) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.btnDelete = btnDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "CustomerTM [id=" + id + ", name=" + name + ", contact=" + contact +
                 ", btnDelete=" + btnDelete + "]";
    }

}
