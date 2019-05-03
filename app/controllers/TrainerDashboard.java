package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class TrainerDashboard extends Controller {

    public static void index() {
        Logger.info("Rendering Trainer Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        render ("trainerdashboard.html", trainer, members);
    }

    public static void deleteMember(Long id) {
        List<Member> members = Member.findAll();
        Member member = Member.findById(id);
        members.remove(member);
        member.delete();
        Logger.info("Deleting Member");
        redirect("/trainerdashboard");
    }

}
