package seraddpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

class Student extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Subject> subjects = new ArrayList<>();
    private HashMap<Subject, Float> marks = new HashMap<>();

    public Student(String name, String surname) {
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
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setMarks(Subject subject, float mark) {
        this.marks.put(subject, mark);
    }

    public HashMap<Subject, Float> getMarks() {
        return marks;
    }

    public void doSmth(String smth, Subject subject) {
        System.out.println(this.getName() + smth);
        subject.getTeachers().get((int) Math.random()).giveMark(this, subject);
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject subject :
                subjects) {
            sub += subject.toString() + marks.get(subject) + "\n";
        }
        return "Студент" + this.getName() + " " + this.getSurname() +
                "його предмети та оцінки: " + sub;
    }
}
