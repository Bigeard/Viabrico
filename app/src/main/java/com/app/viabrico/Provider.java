package com.app.viabrico;
import java.io.Serializable;

public class Provider implements Serializable {
    public int id;
    public String name;
    public String description;
    public String address;
    public String phone;
    public String mail;

    // Constructor :
    public Provider(
            int id,
            String name,
            String description,
            String address,
            String phone,
            String mail
    )
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

}
