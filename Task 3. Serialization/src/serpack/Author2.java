package serpack;

class Author2 extends Human2 {

    public Author2(String name, String surname) {
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

