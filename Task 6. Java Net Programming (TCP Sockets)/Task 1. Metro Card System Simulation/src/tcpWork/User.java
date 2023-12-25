package tcpWork;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private static final DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat dateParser = dateFormatter;

    private String name, surname, patronymic;
    private Boolean sex;
    private Date bDay;

    public User() {
        //
    }

    public User(String name, String surname, String patronymic, Boolean sex, String bDay) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sex = sex;
        try {
            this.bDay = dateParser.parse(bDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Boolean getSex() {
        return sex;
    }

    public String getStrSex(){
        return sex ? "m": "f";
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getbDay() {
        return bDay;
    }

    public void setbDay(String bDay) {
        try {
            this.bDay = dateParser.parse(bDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic + " " + getStrSex() + " " + dateFormatter.format(bDay);
    }
}
