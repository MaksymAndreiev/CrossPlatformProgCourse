package seraddpack;


import java.io.*;


public class Univer2 implements Serializable {

    public static void main(String[] args) {
        Subject2 subject1 = new Subject2("Кросплатформене програмування");
        Subject2 subject2 = new Subject2("Вища математика");
        Plan2 plan = new Plan2("Комп'ютерна інженерія");
        plan.addSubject(subject1);
        plan.addSubject(subject2);
        Student2 me = new Student2("Максим", "Андреєв");
        Teacher2 you = new Teacher2("Марія", "Родінко");
        me.addSubject(subject1);
        me.addSubject(subject2);
        you.addSubject(subject1);
        you.giveMark(me, subject1, 100);
        you.giveMark(me, subject2);
        for (Subject2 s :
                me.getMarks().keySet()) {
            System.out.println(s + ": " + me.getMarks().get(s));
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("univer2.dat"));
            out.writeObject(me);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("univer2.dat"));
            Student2 stoleMyWorksFromGDrive = (Student2) in.readObject();
            for (Subject2 s :
                    stoleMyWorksFromGDrive.getMarks().keySet()) {
                System.out.println(s + ": " + stoleMyWorksFromGDrive.getMarks().get(s));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
