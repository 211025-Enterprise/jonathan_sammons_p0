import java.io.Serializable;

public class user implements Serializable {

private String username;
private String password;

public user(){}


    public user(String username, String password){
    this.username = username;
    this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
