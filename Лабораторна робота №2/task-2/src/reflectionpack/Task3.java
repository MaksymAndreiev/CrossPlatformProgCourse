package reflectionpack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task3 {
    public static void main(String[] args) {
        String str = "Hello World!";
        System.out.println("Test " + "\"" + str + "\"");
        System.out.println("Типы: [String], значения: [\"l\"]");
        GetInfo3.getInfo(str, "indexOf", "l");
        System.out.println("Типы: [String, int], значения: [\"l\", 4]");
        GetInfo3.getInfo(str, "indexOf", "l", 4);
    }
}

class GetInfo3 {
    static void getInfo(Object o, String m, Object... args) {
        Class[] checkPrim = new Class[]{Integer.class, Double.class, Character.class, Float.class, Boolean.class, Byte.class, Short.class, Long.class};
        Class[] prim = new Class[]{int.class, double.class, char.class, float.class, boolean.class, byte.class, short.class, long.class};
        Class[] parameterTypes = new Class[args.length];
        int i = 0;
        for (Object obj :
                args) {
            parameterTypes[i] = obj.getClass();
            int j = 0;
            for (Class cl :
                    checkPrim) {
                if (parameterTypes[i].getName().equals(cl.getName())) {
                    parameterTypes[i] = prim[j];
                }
                j++;
            }
            j = 0;
            i++;
        }
        Class c = o.getClass();
        Method method = null;
        try {
            method = c.getDeclaredMethod(m, parameterTypes);
        } catch (FunctionNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Результат віполнения: " + method.invoke(o, args));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class FunctionNotFoundException extends NoSuchMethodException {
    public FunctionNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}