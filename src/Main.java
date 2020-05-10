/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */

import java.io.*;
import java.time.LocalDate;


/**
 * MP1 - Klasy, atrybuty
 * • Ekstensja
 * • Ekst. - trwałość
 * • Atr. złożony //
 * • Atr. opcjonalny //
 * • Atr. powt.
 * • Atr. klasowy
 * • Atr. pochodny //
 * • Met. klasowa //
 * • Przesłonięcie
 * • przeciążenie
 */


public class Main {

    public static void main(String[] args) {
        File extentFile = new File("MP1_test_file");

        try {
            readExtentFromFile(extentFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (!extentFile.exists()) {
        try {
            createFewObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
        try {
            saveExtentToFile(extentFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFewObjects() throws Exception {
        System.out.println("Srednia wartosc km w powietrzu: " + Pilot.getAverageKmInPlane());
        System.out.println("Ilosc pilotów: " + Pilot.getPilotCount());
        Pilot.setMinAgeToBeAPilot(13);
        for (int i = 1; i < 6; i++) {

            Pilot p = new Pilot("Pilot nr: " + i,
                    "Atomek",
                    LocalDate.now().minusYears(i * 6 * 2),
                    String.valueOf(i * i * i),
                    "Embraer");
            if ((i % 2) == 0) {
                p.setFamilyName("Kowalska");
            }
            p.addKilometersInPlane((long) (i * i * Math.random() * 10000000));
            p.addTypeOfLicence(i + " World");
            p.addTypeOfLicence((int) (i * Math.random() * 10000));
            p.setDateOfMedicalExamsValidity(LocalDate.now().plusDays(12));
            if (p.getCountOfKilometersInPlane() > Pilot.getKmInPlaneToGetBoeingLicense()) {
                p.addPlaneLicence("BOEING");
            }

            if (i % 3 == 0 && (p.getCountOfKilometersInPlane() > Pilot.getKmInPlaneToGetAirbusLicense())) {
                p.addPlaneLicence("Airbus");
            }
        }
        System.out.println("Srednia wartosc km w powietrzu: " + Pilot.getAverageKmInPlane());
        System.out.println("Ilosc pilotów: " + Pilot.getPilotCount());
    }


    public static void readExtentFromFile(File file) throws Exception {
        if (file.exists()) {
            ObjectInputStream streamInput = null;
            try {
                streamInput = new ObjectInputStream(new FileInputStream(file));
                ObjectPlus.getExtent(streamInput);
                System.out.print("Size read from file: ");
                Pilot.setPilotCount(ObjectPlus.extentSize(Pilot.class));

            } catch (Exception c) {
                System.out.println("Class not found.");
                c.printStackTrace();
            } finally {
                if (streamInput != null)
                    streamInput.close();
            }
            ObjectPlus.showExtent(Pilot.class);
        }
    }

    public static void saveExtentToFile(File file) throws IOException {
        ObjectOutputStream streamOutput = null;
        try {
            streamOutput = new ObjectOutputStream(new FileOutputStream(file));
            ObjectPlus.saveExtent(streamOutput);
            System.out.print("Size saved to file: ");
            Pilot.setPilotCount(ObjectPlus.extentSize(Pilot.class));
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            if (streamOutput != null) {
                streamOutput.close();
            }
        }
    }
}
