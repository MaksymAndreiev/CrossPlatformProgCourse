package reflectionpack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine();
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        Class<?> object = null;
        try {
            object = Class.forName(className);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(object);
        System.out.println(ANSI_YELLOW + "Пакет: " + ANSI_RESET + object.getPackage());
        System.out.println(ANSI_YELLOW + "Модифікатори: " + ANSI_RESET + Modifier.toString(object.getModifiers()));
        System.out.println(ANSI_YELLOW + "Ім'я аналізующого класу: " + ANSI_RESET + object.getName());
        System.out.println(ANSI_YELLOW + "Просте ім'я аналізующого класу: " + ANSI_RESET + object.getSimpleName());
        System.out.println(ANSI_YELLOW + "Базовий клас: " + ANSI_RESET + object.getSuperclass());
        System.out.println(ANSI_YELLOW + "Реалізуючі інтерфейси:" + ANSI_RESET);
        for (Class cls : object.getInterfaces()) {
            System.out.println(cls.getSimpleName());
        }
        System.out.println(ANSI_YELLOW + "Поля класа:" + ANSI_RESET);
        for (Field field :
                object.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field);
        }
        System.out.println(ANSI_YELLOW + "Конструктори класа:" + ANSI_RESET);
        for (Constructor cons :
                object.getDeclaredConstructors()) {
            cons.setAccessible(true);
            System.out.println(cons);
        }
        System.out.println(ANSI_YELLOW + "Методи класу:" + ANSI_RESET);
        for (Method meth :
                object.getDeclaredMethods()) {
            meth.setAccessible(true);
            System.out.println(meth);
        }
    }
}