package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/19/2016.
 */
public class Goal {
    private int id;
    private int idUser;
    private String targetName;
    private int targetNumber;
    private String targetFrequency;

    public Goal() {
    }

    public Goal(int id, int idUser, String targetName, int targetNumber) {
        this.id = id;
        this.idUser = idUser;
        this.targetName = targetName;
        this.targetNumber = targetNumber;
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

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public String getTargetFrequency() {
        return targetFrequency;
    }

    public void setTargetFrequency(String targetFrequency) {
        this.targetFrequency = targetFrequency;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", targetName='" + targetName + '\'' +
                ", targetNumber=" + targetNumber +
                ", targetFrequency='" + targetFrequency + '\'' +
                '}';
    }
}
