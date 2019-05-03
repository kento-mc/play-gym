package models;

import controllers.Accounts;
import controllers.GymUtility;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Person
{
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public String gender;
    public String password;
    public double height;
    public double startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String firstName, String lastName, String gender, String email, String password, String address, double height, double startingWeight)
    {
        super(email, firstName, lastName, address, gender, password);
        this.height = height;
        this.startingWeight = startingWeight;
    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    /*
    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
    */

    public double calculateBMI(Long id)
    {
        if (!assessments.isEmpty()) {
            Member member = Member.findById(id);
            Assessment currentAssessment = assessments.get(0);
            double bmi = GymUtility.calculateBMI(member, currentAssessment);
            return bmi;
        } else {
            return 0.0;
        }
    }

    public String bmiCategory(Long id)
    {
        String str = GymUtility.determineBMICategory(calculateBMI(id));
        return str;
    }

    public boolean isIdealWeight(Long id)
    {
        if (!assessments.isEmpty()) {
            Member member = Member.findById(id);
            Assessment currentAssessment = assessments.get(0);
            boolean ideal = GymUtility.isIdealBodyWeight(member, currentAssessment);
            return ideal;
        } else {
            return false;
        }
    }

    public String numAssessments() {
        if (assessments.size() == 1) {
            return assessments.size() + " assessment";
        } else {
            return assessments.size() + " assessments";
        }
    }

    public static void updateTrend(Long id) {
        Member member = Member.findById(id);
        if (member.assessments.size() >= 2)
        {
            for (int i = 0; i < member.assessments.size() - 1; i++)
            {
                if (member.assessments.get(i).weight < member.assessments.get(i+1).weight) {
                    member.assessments.get(i).setTrendIsPositive(true);
                } else {
                    member.assessments.get(i).setTrendIsPositive(false);

                }
            }
        }
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        if (gender.charAt(0) == 'M' || gender.charAt(0) == 'm') {
            this.gender = "M";
        } else if (gender.charAt(0) == 'M' || gender.charAt(0) == 'f') {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

