/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */


import java.util.Date;


public abstract class Person extends ObjectPlus {
    private String name; //atrybut wymagany, prosty, obiektu
    private String surname; //atrybut wymagany, prosty, obiektu
    private String maidenName; // atrybut opcjonalny, obiektu, prosty
    private Date dateOfBirth; // atrybut wymagany, złożony, obiektu,
    private int age; //atrybut pochodny, wyliczalny na podstawie daty urodzenia oraz daty aktualnej.

    protected static final long YEAR_IN_MILLIS = 31556952000L; //atrybut klasowy, przechowujaca wartosc 1 roku w millis

    public Person(String name, String surname, Date dateOfBirth) {
        if (name == null) {
            throw new NullPointerException("Name is required field");
        } else {
            this.name = name;
        }
        if (surname == null) {
            throw new NullPointerException("Surname is required field");
        } else {
            this.surname = surname;
        }
        if (dateOfBirth == null) {
            throw new NullPointerException("Date of birth is required field");
        } else {
            this.dateOfBirth = dateOfBirth;
        }
        setAge();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return (int) (((System.currentTimeMillis() - this.dateOfBirth.getTime()) / YEAR_IN_MILLIS));
    }

    public void setAge() {
        this.age = (int) ((System.currentTimeMillis() - this.dateOfBirth.getTime()) / YEAR_IN_MILLIS);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                (maidenName != null ? ", maidenName='" + maidenName + '\'' : "") +
                ", dateOfBirth=" + dateOfBirth.toInstant() +
                ", age=" + age +
                '}';
    }
}
