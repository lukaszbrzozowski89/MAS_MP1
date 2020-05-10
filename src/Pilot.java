/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Pilot extends Person {

    private static int maxAgeToBeAPilot = 60;
    private static int minAgeToBeAPilot = 18;
    private static long kmInPlaneToGetBoeingLicense = 20000;
    private static long averageCountOfKilometersInPlane;
    private static long kmInPlaneToGetAirbusLicense;

    private long countOfKilometersInPlane = 0;
    private int id;
    private ArrayList<String> planeLicence = new ArrayList<>();
    private boolean isValidMedicalExam;
    private LocalDate dateOfMedicalExamsValidity;
    private ArrayList<Object> licence = new ArrayList<>();

    private static int pilotCount = 0;
    private boolean isActivePilot;
    public String licenseNumber;


    public Pilot(String name, String surname, LocalDate dateOfBirth, String licenseNumber,
                 String planeLicence) throws Exception {
        super(name, surname, dateOfBirth);
        if (getAge(dateOfBirth) < minAgeToBeAPilot) {
            throw new Exception("You are too young to be a pilot");
        }
        if (licenseNumber == null || licenseNumber.isEmpty()) {
            throw new Exception("License is required to be a pilot!");
        }
        this.licenseNumber = licenseNumber;
        this.planeLicence.add(planeLicence);
        pilotCount++;

        this.id = pilotCount;
    }

    public static void setMaxAgeToBeAPilot(int maxAge) {
        maxAgeToBeAPilot = maxAge;
    }

    public static void setMinAgeToBeAPilot(int minAge) {
        minAgeToBeAPilot = minAge;
    }

    public static void changeKmInPlaneToGetBoeingLicense(long km) {
        kmInPlaneToGetBoeingLicense = km;
    }

    public static void changeKmInPlaneToGetAirbusLicense(long km) {
        kmInPlaneToGetAirbusLicense = km;
    }

    public static long getKmInPlaneToGetBoeingLicense() {
        return kmInPlaneToGetBoeingLicense;
    }


    public static long getKmInPlaneToGetAirbusLicense() {
        return kmInPlaneToGetAirbusLicense;
    }

    public int getNumberOfPlaneLicences(Pilot pilot){
        return pilot.getPlaneLicence().size();
    }

    public int getNumberOfLicences(Pilot pilot){
        return pilot.getLicence().size();
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

    public boolean getIsValidMedicalExam(LocalDate dateOfMedicalExamsValidity) {
        return (System.currentTimeMillis() - dateOfMedicalExamsValidity.toEpochDay()) < 0;
    }

    public LocalDate getDateOfMedicalExamsValidity() {
        return dateOfMedicalExamsValidity;
    }

    public void setDateOfMedicalExamsValidity(LocalDate dateOfMedicalExamsValidity) {
        this.dateOfMedicalExamsValidity = dateOfMedicalExamsValidity;
        if (getIsValidMedicalExam(dateOfMedicalExamsValidity)) {
            isValidMedicalExam = true;
        }
        setActivePilot(isValidMedicalExam);
    }


    public void addTypeOfLicence(String typeOfLicence) {
        this.licence.add(typeOfLicence);
    }

    public void addTypeOfLicence(int typeOfLicence) {
        this.licence.add(typeOfLicence);
    }

    public void removeTypeOfLicence(int typeOfLicence) {
        this.licence.remove(typeOfLicence);
    }

    public void addKilometersInPlane(long km) {
        this.countOfKilometersInPlane += km;
        setAverageKmInPlane(this);
    }

    public static void setPilotCount(int pilotCount) {
        Pilot.pilotCount = pilotCount;
    }

    public static int getPilotCount() {
        return pilotCount;
    }

    /**
     * Class method which set average km in plane counted while adding new km in object Pilot
     *
     * @param pilot
     */
    public static void setAverageKmInPlane(Pilot pilot) {
        averageCountOfKilometersInPlane += (pilot.countOfKilometersInPlane - averageCountOfKilometersInPlane) / pilotCount;
    }


    /**
     * Class method which returns average count of KM in plane of all object in that class.
     *
     * @return average kilometers of all objects
     */
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
     * When:
     * 1. pilot reach retirement age (60th birthday): is not active temporarily!
     * 2. pilot doesn't have actual medical exams - is not active
     *
     * @param activePilot - parameter can set which pilot is active employee
     */
    public void setActivePilot(boolean activePilot) {
        if (getAge(this.getDateOfBirth()) < maxAgeToBeAPilot && isValidMedicalExam)
            isActivePilot = activePilot;
        else
            isActivePilot = false;

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
                "numberOfPlaneLicences=" + getNumberOfPlaneLicences(this) +
                ", isValidMedicalExam=" + isValidMedicalExam +
                (dateOfMedicalExamsValidity != null ? ", dateOfMedicalExamsValidity=" + dateOfMedicalExamsValidity : "") +
                ", licence=" + licence +
                ", isActivePilot=" + isActivePilot +
                '}';
    }
}
