package controllers;

import models.*;
import java.util.List;
import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller{

    public static void index () {
        Logger.info("Rendering admin page");
        List<Assessment> assessment = Assessment.findAll();
        render("admin.html", assessment);
    }
}
