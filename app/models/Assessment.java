package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Assessment extends Model
{
    public Date dateTime;
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public boolean trendIsPositive;
    public String comment;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Comment> comments = new ArrayList<Comment>();

    public Assessment(Date dateTime, double weight, double chest, double thigh, double upperArm, double waist, double hips)
    {
        this.dateTime = dateTime;
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.trendIsPositive = true;
        this.comment = "";
    }

    public String convertDateTime(Date dateTime) {
        DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy  HH:mm:ss");
        String date = dateFormat.format(dateTime);
        return date;
    }

    public void setTrendIsPositive(boolean trendIsPositive) {
        this.trendIsPositive = trendIsPositive;
    }

    public void setComment(Comment comment) {
        this.comment = comment.comment;
    }

    public Date getDateTime() {
        return dateTime;
    }
}