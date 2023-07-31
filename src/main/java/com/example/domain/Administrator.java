package main.java.com.example.domain;

public class Administrator {
    private Integer id;
    private String name;
    private String mailAdress;
    private String password;
    
    public Administrator() {
    }
    
    public Administrator(Integer id, String name, String mailAdress, String password) {
        this.id = id;
        this.name = name;
        this.mailAdress = mailAdress;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAdress() {
        return mailAdress;
    }
    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Administrator [id=" + id + ", name=" + name + ", mailAdress=" + mailAdress + ", password=" + password
                + "]";
    }
    
}
