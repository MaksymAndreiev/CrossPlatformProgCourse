package seraddpack;

import java.io.Serializable;
import java.util.ArrayList;

class Teacher extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Subject> subjects = new ArrayList<>();

    public Teacher(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.setTeacher(this);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void giveMark(Student student, Subject subject) {
        if (this.subjects.contains(subject)) {
            float mark = (float) Math.random() * 100;
            student.setMarks(subject, mark);
        } else {
            System.out.println("Цей викладач не веде цей предмет");
        }
    }

    public void giveMark(Student student, Subject subject, float mark) {
        if (this.subjects.contains(subject)) {
            student.setMarks(subject, mark);
        } else {
            System.out.println("Цей викладач не веде цей предмет");
        }
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject subject :
                subjects) {
            sub += subject.toString() + "\n";
        }
        return "Викладач " + this.getName() + " " + this.getSurname() +
                "його предмети: " + sub;
    }
}

