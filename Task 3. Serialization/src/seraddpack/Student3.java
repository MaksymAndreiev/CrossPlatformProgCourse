package seraddpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;

class Student3 extends Human3 implements Externalizable {
    private ArrayList<Subject3> subjects = new ArrayList<>();
    private HashMap<Subject3, Float> marks = new HashMap<>();

    public Student3() {
    }

    public Student3(String name, String surname) {
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
    }

    public ArrayList<Subject3> getSubjects() {
        return subjects;
    }

    public void setMarks(Subject3 subject, float mark) {
        this.marks.put(subject, mark);
    }

    public HashMap<Subject3, Float> getMarks() {
        return marks;
    }

    public void doSmth(Subject3 subject) {
        subject.getTeachers().get((int) Math.random()).giveMark(this, subject);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getSurname());
        out.writeInt(subjects.size());
        for (Externalizable subject :
                subjects) {
            subject.writeExternal(out);
        }
        out.writeInt(marks.size());
        for (float mark :
                marks.values()) {
            out.writeFloat(mark);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName((String) in.readObject());
        setSurname((String) in.readObject());
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            Subject3 subject = new Subject3();
            subject.readExternal(in);
            subjects.add(subject);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            marks.put(subjects.get(i), in.readFloat());
        }
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject3 subject :
                subjects) {
            sub += subject.toString() + marks.get(subject) + "\n";
        }
        return "Студент" + this.getName() + " " + this.getSurname() +
                "його предмети та оцінки: " + sub;
    }
}

