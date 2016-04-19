package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by Simon on 4/18/2016.
 */
public class Activity {
    private int id;
    private String type;
    private String duration;
    private String date;
    private String feedback;
    private String idDetailedActivity;

    public Activity(){}

    public Activity(String type, String duration, String date, String idDetailedActivity) {
        super();
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.idDetailedActivity = idDetailedActivity;
    }

    public Activity(String type, String duration, String date, String feedback, String idDetailedActivity) {
        super();
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.feedback = feedback;
        this.idDetailedActivity = idDetailedActivity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getIdDetailedActivity() {
        return idDetailedActivity;
    }

    public void setIdDetailedActivity(String idDetailedActivity) {
        this.idDetailedActivity = idDetailedActivity;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", date='" + date + '\'' +
                ", feedback='" + feedback + '\'' +
                ", idDetailedActivity='" + idDetailedActivity + '\'' +
                '}';
    }
}
