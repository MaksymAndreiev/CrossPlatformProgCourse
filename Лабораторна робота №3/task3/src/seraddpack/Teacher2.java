package seraddpack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Teacher2 extends Human2 implements Serializable {
    private transient ArrayList<Subject2> subjects = new ArrayList<>();

    public Teacher2(String name, String surname) {
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

    public void addSubject(Subject2 subject) {
        this.subjects.add(subject);
        subject.setTeacher(this);
    }

    public ArrayList<Subject2> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject2> subjects) {
        this.subjects = subjects;
    }

    public void giveMark(Student2 student, Subject2 subject) {
        if (this.subjects.contains(subject)) {
            float mark = (float) Math.random() * 100;
            student.setMarks(subject, mark);
        } else {
            System.out.println("Цей викладач не веде цей предмет");
        }
    }

    public void giveMark(Student2 student, Subject2 subject, float mark) {
        if (this.subjects.contains(subject)) {
            student.setMarks(subject, mark);
        } else {
            System.out.println("Цей викладач не веде цей предмет");
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getName());
        out.writeObject(getSurname());
        out.writeInt(subjects.size());
        for (Subject2 sub :
                subjects) {
            out.writeObject(sub.getName());
            out.writeObject(sub.getTeachers());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setName((String) in.readObject());
        setSurname((String) in.readObject());
        int subSize = in.readInt();
        ArrayList<Subject2> tmpSub = new ArrayList<>();
        for (int i = 0; i < subSize; i++) {
            String name = (String) in.readObject();
            Subject2 subject2 = new Subject2(name);
            ArrayList<Teacher2> teachers = (ArrayList<Teacher2>) in.readObject();
            subject2.setTeachers(teachers);
            tmpSub.add(subject2);
        }
        setSubjects(tmpSub);
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject2 subject :
                subjects) {
            sub += subject.toString() + "\n";
        }
        return "Викладач " + this.getName() + " " + this.getSurname() +
                "його предмети: " + sub;
    }
}
