package seraddpack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Plan2 implements Serializable {
    private String name;
    private transient ArrayList<Subject2> subjects = new ArrayList<>();

    public Plan2(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSubject(Subject2 subject) {
        subjects.add(subject);
    }

    public ArrayList<Subject2> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject2> subjects) {
        this.subjects = subjects;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(name);
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
        return "Академічний план" +
                "'" + name + '\'' +
                ", навчальні дисципліни:" + sub;
    }
}

