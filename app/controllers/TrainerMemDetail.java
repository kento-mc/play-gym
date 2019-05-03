package controllers;

import models.Assessment;
import models.Comment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverseOrder;

public class TrainerMemDetail extends Controller{

    public static void index(Long id) {
        Logger.info("Rendering Member Detail Page");
        //Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        assessments.sort(Comparator.comparing(Assessment::getDateTime, reverseOrder()));
        member.updateTrend(member.id);
        render ("trainermemdetail.html", member, assessments);
    }

    public static void addComment(Long id, Long assessmentid, String comment) {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        Comment cmt = new Comment(comment);
        assessment.setComment(cmt);
        member.save();
        Logger.info("Adding Comment: " + comment);
        redirect("/members/" + member.id);
    }
}
