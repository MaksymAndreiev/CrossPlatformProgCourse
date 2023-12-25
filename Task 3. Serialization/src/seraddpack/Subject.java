package seraddpack;

import java.io.Serializable;
import java.util.ArrayList;

class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private ArrayList<Teacher> teachers = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return name;
    }

}
