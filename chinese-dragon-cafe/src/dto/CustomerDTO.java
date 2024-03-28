package dto;

public class CustomerDTO {

    private String id;
    private String name;
    private int contact;
 

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, int contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public CustomerDTO(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CustomerDTO [id=" + id + ", name=" + name + ", contact=" + contact + "]";
    }

}
