package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends Record {
    private String surname;
    private String birth;
    private String gender;

    public Person() {
        super();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        try {
            LocalDate.parse(birth);
            this.birth = birth;
        } catch (DateTimeParseException e) {
            this.birth = "[no data]";
            System.out.println("Bad birth date!");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.matches("[MF]")) {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
            System.out.println("Bad gender!");
        }
    }

    @Override
    public String getInfo() {
        return String.format("%s %s", getName(), surname);
    }

    @Override
    public String getDetailedInfo() {
        return getName() + " " + getSurname() + " " + getBirth() + " " + getGender() + " " + getNumber();
    }

    @Override
    String getFields() {
        return "name, surname, birth, gender, number";
    }

    @Override
    void changeField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
                setBirth(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setNumber(value);
                break;
        }
    }

    @Override
    String getField(String field) {
        switch (field) {
            case "name":
                return getName();
            case "surname":
                return getSurname();
            case "birth":
                return getBirth();
            case "gender":
                return getGender();
            case "number":
                return getNumber();
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s",
                getName(), surname, birth, gender, getNumber(), getLastCreated(), getLastEdited());
    }
}
