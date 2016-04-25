package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/18/2016.
 */
public class Activity {
    private int id;
    private int idUser;
    private String type;
    private String duration;
    private String date;
    private String feedback;

    public Activity() {
    }

    public Activity(int id, int idUser, String duration, String type, String date, String feedback) {
        this.id = id;
        this.idUser = idUser;
        this.duration = duration;
        this.type = type;
        this.date = date;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", date='" + date + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
