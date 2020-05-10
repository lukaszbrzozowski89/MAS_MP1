/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */

import java.time.LocalDate;


public abstract class Person extends ObjectPlus {
    private String name;
    private String surname;
    private String familyName;
    private LocalDate dateOfBirth;


    public Person(String name, String surname, LocalDate dateOfBirth) {
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
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public int getAge(LocalDate dateOfBirth) {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
               ", familyName='" + (getFamilyName() != null ?  getFamilyName() : "(no family name)") + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}

