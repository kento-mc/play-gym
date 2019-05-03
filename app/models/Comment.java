package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Comment extends Model
{
    public String comment;

    public Comment(String comment)
    {
        this.comment = comment;
    }
}