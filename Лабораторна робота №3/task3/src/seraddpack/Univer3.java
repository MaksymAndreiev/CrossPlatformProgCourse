package seraddpack;

import java.io.*;


public class Univer3 {

    public static void main(String[] args) {
        Subject3 subject1 = new Subject3("Кросплатформене програмування");
        Subject3 subject2 = new Subject3("Вища математика");
        Plan3 plan = new Plan3("Комп'ютерна інженерія");
        plan.addSubject(subject1);
        plan.addSubject(subject2);
        Student3 me = new Student3("Максим", "Андреєв");
        Teacher3 you = new Teacher3("Марія", "Родінко");
        me.addSubject(subject1);
        me.addSubject(subject2);
        you.addSubject(subject1);
        you.giveMark(me, subject1, 100);
        you.giveMark(me, subject2);
        for (Subject3 s :
                me.getMarks().keySet()) {
            System.out.println(s + ": " + me.getMarks().get(s));
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("univer3.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(me);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("univer3.dat"));
            Student3 stoleMyWorksFromGDrive = (Student3) in.readObject();
            for (Subject3 s :
                    stoleMyWorksFromGDrive.getMarks().keySet()) {
                System.out.println(s + ": " + stoleMyWorksFromGDrive.getMarks().get(s));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
