/*
 * Copyright (c) 2020
 * Łukasz Brzozowski (s17174) @ PJATK
 */


import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Optional;


public abstract class Person extends ObjectPlus {
    private String name; //atrybut prosty, obiektu
    private String surname; //atrybut prosty, obiektu
    private String familyName; // atrybut prosty, opcjonalny, obiektu,
    private LocalDate dateOfBirth; // atrybut złożony, obiektu,


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

