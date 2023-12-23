package reflectionpack;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static reflectionpack.GetInfo2.ANSI_RESET;
import static reflectionpack.GetInfo2.ANSI_YELLOW;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("Введіть ім'я класу:");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine();
        Class<?> cls = null;
        Object obj = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int i = 1;
        HashMap<Integer, Constructor> constructors = new HashMap<>(cls.getConstructors().length);
        for (Constructor constructor :
                cls.getConstructors()) {
            System.out.println(i + ") " + constructor);
            constructors.put(i, constructor);
            i++;
        }
        System.out.println("Введіть номер конструктора:");
        int c = scanner.nextInt();
        Constructor constructor = constructors.get(c);
        ArrayList<Object> parameters = new ArrayList<>();
        if (constructor.getParameters().length > 0) {
            System.out.println("Введіть наступні параметри для створення об'єкту:");
            for (Parameter parameter :
                    constructor.getParameters()) {
                if (parameter.getType().isArray()) {
                    /*System.out.println("");
                    int len = scanner.nextInt();
                    Object array = Array.newInstance(Object.class, len);
                    for (int j = 0; j < len; j++) {
                        Array.set(array, j, );
                    }
                    parameters.add(array);*/
                    System.out.println("Не має можливості");
                    System.exit(0);
                } else {
                    System.out.println("Параметр типу " + parameter.getType().getSimpleName());
                    parameters.add(scanner.next());
                }
            }
            try {
                obj = constructor.newInstance(parameters.toArray());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            try {
                obj = constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ANSI_YELLOW + "Створений об'єкт: " + ANSI_RESET + obj);
        System.out.println(ANSI_YELLOW + "Поля об'єкту та їх значення: " + ANSI_RESET);
        for (Field field:
             cls.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                System.out.println(field+" = "+ field.get(obj).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ANSI_YELLOW + "Методи класу:" + ANSI_RESET);

        HashMap<Integer, Method> methods = new HashMap<>(cls.getMethods().length);
        i = 1;
        for (Method meth :
                cls.getMethods()) {
            System.out.println(i + ") " + meth);
            methods.put(i, meth);
            i++;
        }
        System.out.println("Введіть номер метода:");
        c = scanner.nextInt();
        Method method = methods.get(c);
        parameters = new ArrayList<>();
        if (method.getParameters().length > 0) {
            System.out.println("Введіть наступні параметри для виклику метода:");
            for (Parameter parameter :
                    method.getParameters()) {
                if (parameter.getType().isArray()) {
                    /*System.out.println("");
                    int len = scanner.nextInt();
                    Object array = Array.newInstance(Object.class, len);
                    for (int j = 0; j < len; j++) {
                        Array.set(array, j, );
                    }
                    parameters.add(array);*/
                    System.out.println("Не має можливості");
                    System.exit(0);
                } else {
                    System.out.println("Параметр типу " + parameter.getType().getSimpleName());
                    parameters.add(scanner.next());
                }
            }
            try {
                System.out.println(ANSI_YELLOW + "Результат виклику метода: " + ANSI_RESET + method.invoke(obj, parameters.toArray()));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println(ANSI_YELLOW + "Результат виклику метода: " + ANSI_RESET + method.invoke(obj));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ANSI_YELLOW + "Поля об'єкту та їх значення: " + ANSI_RESET);
        for (Field field:
                cls.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                System.out.println(field+" = "+ field.get(obj).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
