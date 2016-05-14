package domel.ecampus.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillermo on 14/5/16.
 */
public class User {

    @SerializedName("email")
    private String email;

    @SerializedName("pass")
    private String password;

    @SerializedName("in")
    private boolean remembered;

    public User() {
    }

    public User(String email, String password, boolean remembered) {
        this.email = email;
        this.password = password;
        this.remembered = remembered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemembered() {
        return remembered;
    }

    public void setRemembered(boolean remembered) {
        this.remembered = remembered;
    }
}
