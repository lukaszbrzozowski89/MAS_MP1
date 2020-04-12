/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {

        File extentFile = new File("MP1_test_file");
        getExtentFromFile(extentFile);

        if (!extentFile.exists()) {
            createFewObjects();
        }
        saveExtentToFile(extentFile);
    }

    public static void createFewObjects() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        for (int i = 10; i < 100; i++) {
            Date d = dateFormat.parse("19" + (i % 10) + i % 10 + "/04/02");
            Pilot p = new Pilot("Pilor nr: " + i, "Atomek", d, i * i * i, "Airbus");
            p.addKilometersInPlane((long) (i * i * Math.random() * 100000000));
            p.addTypeOfLicence(i + " World");
            p.addTypeOfLicence((int) (i * Math.random() * 10000));
            Date d2 = dateFormat.parse("202" + i % 10 + "/04/02");
            p.setDateOfMedicalExamsValidity(d2);
            if (i % 2 == 0) {
                p.setMaidenName("Nazwisko panieńskie");
                p.addPlaneLicence("Boeing");
            }
        }
        System.out.println(Pilot.getAverageKmInPlane());
    }

    public static void getExtentFromFile(File file) throws Exception {
        if (file.exists()) {
            ObjectInputStream streamInput = null;
            try {
                streamInput = new ObjectInputStream(new FileInputStream(file));
                ObjectPlus.getExtent(streamInput);
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
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            if (streamOutput != null) {
                streamOutput.close();
            }
        }
    }
}
