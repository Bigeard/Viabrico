package com.app.viabrico;
import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String mail;
    public String password;
    public String role;
    // Constructor :
    public User(
            int id,
            String mail,
            String password,
            String role
    )
    {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

}
