package reflectionpack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        String str = "Hello!";
        GetInfo2.fields(str);
        Method method = GetInfo2.open_methods(str);
        GetInfo2.invokeMeth(method, str);
    }
}

class GetInfo2 {
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RESET = "\u001B[0m";

    static void fields(Object o) {
        Class c = o.getClass();
        System.out.println(ANSI_YELLOW + "Поля класа і їх значення:" + ANSI_RESET);
        for (Field field :
                c.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                System.out.println(field);
                System.out.println("Значення:" + field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    static Method open_methods(Object o) {
        Class c = o.getClass();
        ArrayList <Method> list = new ArrayList();
        System.out.println(ANSI_YELLOW + "відкриті методи:" + ANSI_RESET);
        int i = 1;
        for (Method meth :
                c.getMethods()) {
            System.out.println(i + ") " + meth);
            list.add(meth);
            i++;
        }
        System.out.println("Виберіть метод [1, "+(i-1)+"]:");
        Scanner scanner = new Scanner(System.in);
        return list.get(scanner.nextInt()-1);
    }

    static void invokeMeth(Method method, Object o){
        Class c = o.getClass();
        try {
            if (method.getParameterCount() == 0) {
                System.out.println("Результат визову метода: "+method.invoke(o));
            } else {
                System.out.println("Метод має параметри");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}