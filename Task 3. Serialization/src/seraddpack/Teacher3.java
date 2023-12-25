package seraddpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

class Teacher3 extends Human3 implements Externalizable {
    private ArrayList<Subject3> subjects = new ArrayList<>();

    public Teacher3() {
    }

    public Teacher3(String name, String surname) {
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

    public void addSubject(Subject3 subject) {
        this.subjects.add(subject);
        subject.setTeacher(this);
    }


    public ArrayList<Subject3> getSubjects() {
        return subjects;
    }

    public void giveMark(Student3 student, Subject3 subject) {
        if (this.subjects.contains(subject)) {
            float mark = (float) Math.random() * 100;
            student.setMarks(subject, mark);
        } else {
            System.out.println("Цей викладач не веде цей предмет");
        }
    }

    public void giveMark(Student3 student, Subject3 subject, float mark) {
        if (this.subjects.contains(subject)) {
            student.setMarks(subject, mark);
        } else {
            System.out.println("Цей викладач не веде цей предмет");
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getSurname());
        out.writeObject(subjects);
        out.writeInt(subjects.size());
        for (Externalizable subject :
                subjects) {
            subject.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName((String) in.readObject());
        setSurname((String) in.readObject());
        subjects = (ArrayList<Subject3>) in.readObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            Subject3 subject = new Subject3();
            subject.readExternal(in);
            subjects.add(subject);
        }
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject3 subject :
                subjects) {
            sub += subject.toString() + "\n";
        }
        return "Викладач " + this.getName() + " " + this.getSurname() +
                "його предмети: " + sub;
    }
}

