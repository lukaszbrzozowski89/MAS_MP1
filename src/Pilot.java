/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */

import java.util.ArrayList;
import java.util.Date;

public class Pilot extends Person {

    public static int maxAgeToBeAPilot = 60; //atrybut klasowy, maksymalny wiek jaki moze miec pilot
    public static long averageCountOfKilometersInPlane;
    private long countOfKilometersInPlane = 0;
    private int id;
    private ArrayList<String> planeLicence = new ArrayList<>(); //atrybut powtarzalny, prosty, opcjonalny
    private boolean isValidMedicalExam;
    private Date dateOfMedicalExamsValidity;
    private ArrayList<Object> licence = new ArrayList<>(); // atrybut powtarzalny, złożony
    private static int pilotCount = 0;
    private boolean isActivePilot;
    public int licenseNumber; // wymagany, złozony


    public Pilot(String name, String surname, Date dateOfBirth, int licenseNumber, String planeLicence) throws Exception {
        super(name, surname, dateOfBirth);
        if (licenseNumber == 0) {
            throw new Exception("License is required to be a pilot!");
        }
        this.licenseNumber = licenseNumber;
        if (planeLicence == null){
            throw new Exception("Pilot without a licence for plane?");
        }
        this.planeLicence.add(planeLicence);
        pilotCount++;

        this.id = pilotCount;
    }

    public long getCountOfKilometersInPlane() {
        return countOfKilometersInPlane;
    }

    public ArrayList<Object> getLicence() {
        return licence;
    }

    public void setCountOfKilometersInPlane(long countOfKilometersInPlane) {
        this.countOfKilometersInPlane = countOfKilometersInPlane;
    }

    public ArrayList<String> getPlaneLicence() {
        return planeLicence;
    }

    public boolean getIsValidMedicalExam(Date dateOfMedicalExamsValidity) {
        return (System.currentTimeMillis() - dateOfMedicalExamsValidity.getTime()) < 0;
    }

    public Date getDateOfMedicalExamsValidity() {
        return dateOfMedicalExamsValidity;
    }

    public void setDateOfMedicalExamsValidity(Date dateOfMedicalExamsValidity) {
        this.dateOfMedicalExamsValidity = dateOfMedicalExamsValidity;
        if (getIsValidMedicalExam(dateOfMedicalExamsValidity)) {
            isValidMedicalExam = true;
        }
        setActivePilot(isValidMedicalExam);
    }


    public void addTypeOfLicence(String typeOfLicence) {
        this.licence.add(typeOfLicence);
    }

    public void addTypeOfLicence(int license) {
        this.licence.add(license);
    }

    public void removeTypeOfLicence(int typeOfLicence) {
        this.licence.remove(typeOfLicence);
    }

    public void addKilometersInPlane(long km) {
        this.countOfKilometersInPlane += km;
        setAverageKmInPlane(this);
    }

    public static void setAverageKmInPlane(Pilot pilot) {
        averageCountOfKilometersInPlane += (pilot.countOfKilometersInPlane - averageCountOfKilometersInPlane) / pilotCount;
    }

    public static long getAverageKmInPlane() {
        return averageCountOfKilometersInPlane;
    }

    public void addPlaneLicence(String plane) {
        this.planeLicence.add(plane);
    }

    /**
     * Method which returns {unique} ID of pilot
     *
     * @return pilot ID
     */
    public int getId() {
        return id;
    }

    public boolean isActivePilot() {
        return isActivePilot;
    }

    /**
     * Method used to set pilot activity-
     * When pilot:
     * 1. pilot reach retirement age (60th birthday): is not active temporarily!
     * 2. pilot doesn't have actual medical exams - is not active
     *
     * @param activePilot - parameter can set which pilot is active employee
     */
    public void setActivePilot(boolean activePilot) {
        if (getAge() < maxAgeToBeAPilot && isValidMedicalExam)
            isActivePilot = activePilot;
        else
            isActivePilot = false;

    }

    /**
     * Override method from Person class.
     *
     * @return age of Pilot
     */
    @Override
    public int getAge() {
        return super.getAge();
    }

    /**
     * Override method from Person class.
     *
     * @return super.toString & toString from Pilot class
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t\t" +
                "Pilot{" +
                "countOfKilometersInPlane=" + countOfKilometersInPlane +
                ", id=" + id +
                ", licenseNumber=" + licenseNumber +
                ", planeLicence=" + planeLicence +
                ", isValidMedicalExam=" + isValidMedicalExam +
                ", dateOfMedicalExamsValidity=" + dateOfMedicalExamsValidity +
                ", licence=" + licence +
                ", isActivePilot=" + isActivePilot +
                '}';
    }
}
