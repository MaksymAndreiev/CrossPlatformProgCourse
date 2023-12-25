package registry;

import java.io.Serializable;

public class Participant implements Serializable {
    private String name;
    private String surname;
    private String organization;
    private String report;
    private String email;

    public Participant(String name, String surname, String organization, String report, String email) {
        this.name = name;
        this.surname = surname;
        this.organization = organization;
        this.report = report;
        this.email = email;
    }

    public Participant() {

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getOrganization() {
        return organization;
    }

    public String getReport() {
        return report;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", surname: " + surname +
                ", organization: " + organization +
                ", report: " + report +
                ", email: " + email;
    }
}
