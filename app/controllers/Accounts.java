package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void register(String firstname, String lastname, String gender, String email, String password, String address, double height, double startingWeight)
    {
        Logger.info("Registering new user: " + email);
        Member member = new Member(firstname, lastname, gender, email, password, address, height, startingWeight);
        member.save();
        authenticate(email, password);
        //redirect("/");
    }

    public static void memberUpdate(String firstname, String lastname, String gender, String email, String password, String address, double height, double startingWeight) {
        Member member = Accounts.getLoggedInMember();
        if (member != null) {
            Logger.info("Updating Member Info");
            if (!firstname.isEmpty()) {
                member.setFirstName(firstname);
            }
            if (!lastname.isEmpty()) {
                member.setLastName(lastname);
            }
            if (!gender.isEmpty()) {
                member.setGender(gender);
            }
            if (!email.isEmpty()) {
                member.setEmail(email);
            }
            if (!password.isEmpty()) {
                member.setPassword(password);
            }
            if (!address.isEmpty()) {
                member.setAddress(address);
            }
            if (height > 0) {
                member.setHeight(height);
            }
            if (startingWeight > 0) {
                member.setStartingWeight(startingWeight);
            }
            member.save();
            redirect("/member");
        }
    }

    public static void trainerUpdate(String firstname, String lastname, String gender, String email, String password, String address) {
        Trainer trainer = Accounts.getLoggedInTrainer();
        if (trainer != null) {
            Logger.info("Updating Trainer Info");
            if (!firstname.isEmpty()) {
                trainer.setFirstName(firstname);
            }
            if (!lastname.isEmpty()) {
                trainer.setLastName(lastname);
            }
            if (!gender.isEmpty()) {
                trainer.setGender(gender);
            }
            if (!email.isEmpty()) {
                trainer.setEmail(email);
            }
            if (!password.isEmpty()) {
                trainer.setPassword(password);
            }
            if (!address.isEmpty()) {
                trainer.setAddress(address);
            }
            trainer.save();
            redirect("/trainer");
        }
    }

    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ": " + password);

        Member member = Member.findByEmail(email);
        Trainer trainer = Trainer.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        } else if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainer.id);
            redirect ("/trainerdashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static Trainer getLoggedInTrainer()
    {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }
}