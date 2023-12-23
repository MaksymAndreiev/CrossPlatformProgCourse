package seraddpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

class Plan3 implements Externalizable {
    private String name;
    private ArrayList<Subject3> subjects = new ArrayList<>();

    public Plan3(String name) {
        this.name = name;
    }

    public void addSubject(Subject3 subject) {
        subjects.add(subject);
    }

    public ArrayList<Subject3> getSubjects() {
        return subjects;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(subjects.size());
        for (Externalizable subject :
                subjects) {
            subject.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
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
        return "Академічний план" +
                "'" + name + '\'' +
                ", навчальні дисципліни:" + sub;
    }
}

