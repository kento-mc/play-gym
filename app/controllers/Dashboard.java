package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Collections.reverseOrder;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    assessments.sort(Comparator.comparing(Assessment::getDateTime, reverseOrder()));
    member.updateTrend(member.id);
    render ("dashboard.html", member, assessments);
  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    Member member = Accounts.getLoggedInMember();
    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    //dateFormat.format(date);
    Assessment assessment = new Assessment(date, weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.save();
    Logger.info("Adding Assessment");
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id, Long assessmentid) {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting Assessment");
    redirect("/dashboard");
  }

}
