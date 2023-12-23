package seraddpack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

class Student2 extends Human2 implements Serializable {
    private transient ArrayList<Subject2> subjects = new ArrayList<>();
    private transient HashMap<Subject2, Float> marks = new HashMap<>();

    public Student2(String name, String surname) {
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
    }

    public ArrayList<Subject2> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject2> subjects) {
        this.subjects = subjects;
    }

    public void setMarks(Subject2 subject, float mark) {
        this.marks.put(subject, mark);
    }

    public void setMarks(HashMap<Subject2, Float> marks) {
        this.marks = marks;
    }

    public HashMap<Subject2, Float> getMarks() {
        return marks;
    }

    public void doSmth(Subject2 subject) {
        subject.getTeachers().get((int) Math.random()).giveMark(this, subject);
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
        out.writeInt(marks.size());
        for (Subject2 sub :
                marks.keySet()) {
            out.writeObject(sub.getName());
            out.writeObject(sub.getTeachers());
        }
        for (float mark :
                marks.values()) {
            out.writeFloat(mark);
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
        int markSize = in.readInt();
        tmpSub = new ArrayList<>();
        for (int i = 0; i < markSize; i++) {
            String name = (String) in.readObject();
            Subject2 subject2 = new Subject2(name);
            ArrayList<Teacher2> teachers = (ArrayList<Teacher2>) in.readObject();
            subject2.setTeachers(teachers);
            tmpSub.add(subject2);
        }
        ArrayList<Float> marks = new ArrayList<>();
        for (int i = 0; i < markSize; i++) {
            marks.add(in.readFloat());
        }
        HashMap<Subject2, Float> tmp = new HashMap<>();
        for (int i = 0; i < markSize; i++) {
            tmp.put(tmpSub.get(i), marks.get(i));
        }
        setMarks(tmp);
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject2 subject :
                marks.keySet()) {
            sub += subject.toString() + " " + marks.get(subject) + "\n";
        }
        return "Студент " + this.getName() + " " + this.getSurname() +
                " його предмети та оцінки: " + sub;
    }
}
