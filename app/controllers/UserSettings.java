package controllers;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverseOrder;

public class UserSettings extends Controller {

    public static void memberIndex() {
        Logger.info("Rendering Member Settings");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        assessments.sort(Comparator.comparing(Assessment::getDateTime, reverseOrder()));
        render ("membersettings.html", member, assessments);
    }

    public static void trainerIndex() {
        Logger.info("Rendering Trainer Settings");
        Trainer trainer = Accounts.getLoggedInTrainer();
        render ("trainersettings.html", trainer);
    }

}
