package seraddpack;

import java.io.*;

public class Univer1 {

    public static void serializeObject(String filename, Object obj) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(obj);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Object obj = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            obj = is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Subject subject1 = new Subject("Кросплатформене програмування");
        Subject subject2 = new Subject("Вища математика");
        Plan plan = new Plan("Комп'ютерна інженерія");
        plan.addSubject(subject1);
        plan.addSubject(subject2);
        Student me = new Student("Максим", "Андреєв");
        Teacher you = new Teacher("Марія", "Родінко");
        me.addSubject(subject1);
        me.addSubject(subject2);
        you.addSubject(subject1);
        you.giveMark(me, subject1, 100);
        you.giveMark(me, subject2);
        for (Subject s :
                me.getMarks().keySet()) {
            System.out.println(s + ": " + me.getMarks().get(s));
        }
        serializeObject("univer1.dat", me);
        Student stoleMyWorksFromGDrive = (Student) deSerializeObject("univer1.dat");
        for (Subject s :
                stoleMyWorksFromGDrive.getMarks().keySet()) {
            System.out.println(s + ": " + stoleMyWorksFromGDrive.getMarks().get(s));
        }
    }
}
