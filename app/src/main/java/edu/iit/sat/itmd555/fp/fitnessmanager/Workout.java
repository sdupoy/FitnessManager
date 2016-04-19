package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by Simon on 4/19/2016.
 */
public class Workout {
    int nbOfRep;
    String typeOfRep;

    public Workout() {
    }

    public Workout(String typeOfRep, int nbOfRep) {
        this.typeOfRep = typeOfRep;
        this.nbOfRep = nbOfRep;
    }

    public int getNbOfRep() {
        return nbOfRep;
    }

    public void setNbOfRep(int nbOfRep) {
        this.nbOfRep = nbOfRep;
    }

    public String getTypeOfRep() {
        return typeOfRep;
    }

    public void setTypeOfRep(String typeOfRep) {
        this.typeOfRep = typeOfRep;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "nbOfRep=" + nbOfRep +
                ", typeOfRep='" + typeOfRep + '\'' +
                '}';
    }
}
