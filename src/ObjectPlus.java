/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class ObjectPlus implements Serializable {

    private static final long serialVersionUID = 11112L; // User ID required do serializable

    private static Hashtable<Class<? extends ObjectPlus>, ArrayList<ObjectPlus>> extent = new Hashtable<>();

    public ObjectPlus() {
        ArrayList<ObjectPlus> ext;
        Class<? extends ObjectPlus> className = this.getClass();

        if (extent.containsKey(className)) {
            ext = extent.get(className);
        } else {
            ext = new ArrayList<>();
            extent.put(className, ext);
        }
        ext.add(this);
    }

    public static void saveExtent(ObjectOutputStream oos) throws IOException {
        oos.writeObject(extent);
    }

    public static void getExtent(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        extent = (Hashtable<Class<? extends ObjectPlus>, ArrayList<ObjectPlus>>) ois.readObject();
    }

    public static void showExtent(Class<? extends ObjectPlus> className) throws Exception {
        ArrayList<ObjectPlus> extentList;

        if (extent.containsKey(className)) {
            extentList = extent.get(className);
        } else {
            throw new Exception("Unknown class " + className);
        }

        System.out.println("Extent of class: " + className.getSimpleName());

        for (Object obj : extentList) {
            System.out.println(obj + "\n");
        }
    }
}