package com.javarush.task.task20.task2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class User {
    private static final String NONE = "none";
    private String firstName;
    private String lastName;
    private Date birthDate;
    private boolean isMale;
    private Country country;

    public User() {
    }

    public User(String firstName, boolean isMale) {
        this.firstName = firstName;
        this.isMale = isMale;
    }

    public User(String firstName, String lastName, boolean isMale) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isMale = isMale;
    }

    public User(String firstName, String lastName, Date birthDate, boolean isMale, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static enum Country {
        UKRAINE("Ukraine"),
        RUSSIA("Russia"),
        OTHER("Other");

        private String name;

        private Country(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return this.name;
        }

        public static Country getCountry(String name) {
            switch (name) {
                case "Ukraine": return UKRAINE;
                case "Russia": return RUSSIA;
                case "Other": return OTHER;
                default: return null;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isMale != user.isMale) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        return country == user.country;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (isMale ? 1 : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public void save (PrintStream printStream) throws Exception {
        if (firstName == null || firstName.isEmpty()) {
            throw new Exception("Имя пользователя не может быть пустым!");
        }
        printStream.println(String.format("%s,%s,%s,%s,%s",
                firstName,
                lastName == null ? NONE : lastName,
                birthDate == null ? NONE : String.valueOf(birthDate.getTime()),
                isMale,
                country == null ? NONE : country.getDisplayName()));
        printStream.flush();
    }

    public void load(BufferedReader reader) throws IOException {
        String[] fields = reader.readLine().split(",");
        firstName = fields[0];
        if (!NONE.equals(fields[1])) {lastName = fields[1];}
        if (!NONE.equals(fields[2])) {birthDate = new Date(Long.parseLong(fields[2]));}
        isMale = Boolean.parseBoolean(fields[3]);
        if (!NONE.equals(fields[4])) {country = Country.getCountry(fields[4]);}
    }
}

