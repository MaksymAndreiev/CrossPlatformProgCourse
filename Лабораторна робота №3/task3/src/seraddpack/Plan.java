package seraddpack;

import java.io.Serializable;
import java.util.ArrayList;

class Plan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private ArrayList<Subject> subjects = new ArrayList<>();

    public Plan(String name) {
        this.name = name;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        String sub = "";
        for (Subject subject :
                subjects) {
            sub += subject.toString() + "\n";
        }
        return "Академічний план" +
                "'" + name + '\'' +
                ", навчальні дисципліни:" + sub;
    }
}
