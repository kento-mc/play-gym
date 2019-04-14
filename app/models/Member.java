package models;

import controllers.Accounts;
import controllers.GymUtility;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model
{
    public String firstname;
    public String lastname;
    public String gender;
    public String email;
    public String password;
    public String address;
    public double height;
    public double startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String firstname, String lastname, String gender, String email, String password, String address, double height, double startingWeight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.height = height;
        this.startingWeight = startingWeight;
    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public double calculateBMI()
    {
        if (!assessments.isEmpty()) {
            Member member = Accounts.getLoggedInMember();
            Assessment currentAssessment = assessments.get(assessments.size() - 1);
            double bmi = GymUtility.calculateBMI(member, currentAssessment);
            return bmi;
        } else {
            return 0.0;
        }
    }

    public String bmiCategory()
    {
        String str = GymUtility.determineBMICategory(calculateBMI());
        return str;
    }

    public boolean isIdealWeight()
    {
        if (!assessments.isEmpty()) {
            Member member = Accounts.getLoggedInMember();
            Assessment currentAssessment = assessments.get(assessments.size() - 1);
            boolean ideal = GymUtility.isIdealBodyWeight(member, currentAssessment);
            return ideal;
        } else {
            return false;
        }
    }

}

