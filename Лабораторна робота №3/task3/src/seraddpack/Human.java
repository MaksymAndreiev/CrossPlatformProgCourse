package seraddpack;

import java.io.Serializable;

abstract class Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient String name;
    private transient String surname;

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
}
