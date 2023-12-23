package seraddpack;

import java.util.ArrayList;

class Subject2 {
    private String name;
    private ArrayList<Teacher2> teachers = new ArrayList<>();

    public Subject2(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher2 teacher) {
        this.teachers.add(teacher);
    }

    public ArrayList<Teacher2> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher2> teachers) {
        this.teachers = teachers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

