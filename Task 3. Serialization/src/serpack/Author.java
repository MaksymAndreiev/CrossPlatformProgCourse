package serpack;

import java.io.Serializable;

class Author extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    public Author(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    @Override
    public String toString() {
        return "Автор: " + getName() + " " + getSurname();
    }
}

