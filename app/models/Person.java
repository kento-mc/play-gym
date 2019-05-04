package models;

import play.db.jpa.Model;

public class Person extends Model {
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public String gender;
    public String password;

    public Person() {}

    public Person(String email, String firstName, String lastName, String address, String gender, String password) {
        this.email = email;
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setGender(gender);
        this.password = password;

    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 15) {
            this.firstName = firstName.substring(0, 15);
        } else {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 15) {
            this.lastName = lastName.substring(0, 15);
        } else {
            this.lastName = lastName;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        if (gender.charAt(0) == 'M' || gender.charAt(0) == 'm') {
            this.gender = "M";
        } else if (gender.charAt(0) == 'F' || gender.charAt(0) == 'f') {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
