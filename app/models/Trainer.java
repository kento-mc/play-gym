package models;

import javax.persistence.Entity;

@Entity
public class Trainer extends Person {
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public String gender;
    public String password;


    public Trainer(String firstName, String lastName, String gender, String email, String password, String address, double height, double startingWeight)
    {
        super(email, firstName, lastName, address, gender, password);
    }

    public static Trainer findByEmail(String email)
    {
        return find("email", email).first();
    }
}
