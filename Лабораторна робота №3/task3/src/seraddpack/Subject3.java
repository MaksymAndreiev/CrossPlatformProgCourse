package seraddpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

class Subject3 implements Externalizable {
    private String name;
    private ArrayList<Teacher3> teachers = new ArrayList<>();

    public Subject3() {
    }

    public Subject3(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher3 teacher) {
        this.teachers.add(teacher);
    }

    public ArrayList<Teacher3> getTeachers() {
        return teachers;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(teachers.size());
        for (Teacher3 teacher :
                teachers) {
            out.writeObject(teacher.getName());
            out.writeObject(teacher.getSurname());
            Subject3[] s = new Subject3[teacher.getSubjects().size()];
            s = teacher.getSubjects().toArray(s);
            out.writeObject(s);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String name = (String) in.readObject();
            String surname = (String) in.readObject();
            Subject3[] s = (Subject3[]) in.readObject();
            Teacher3 teacher = new Teacher3(name, surname);
            for (Subject3 sub :
                    s) {
                teacher.addSubject(sub);
            }
            teachers.add(teacher);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

