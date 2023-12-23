package reflectionpack;

import java.lang.reflect.Array;


public class Task4 {
    public static void main(String[] args) {
        String className = "java.lang.String";
        Object array = null;
        Class cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            if (className.equals("byte"))
                cls = byte.class;
            if (className.equals("short"))
                cls = short.class;
            if (className.equals("int"))
                cls = int.class;
            if (className.equals("long"))
                cls = long.class;
            if (className.equals("char"))
                cls = char.class;
            if (className.equals("float"))
                cls = float.class;
            if (className.equals("double"))
                cls = double.class;
            if (className.equals("boolean"))
                cls = boolean.class;
            if (className.equals("void"))
                cls = void.class;
        }
        array = ArrayWork.create(cls, 5);
        System.out.println(ArrayWork.toString(array));
        array = ArrayWork.create(cls, 3, 2);
        System.out.println(ArrayWork.toString(array));
        className = "int";
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            if (className.equals("byte"))
                cls = byte.class;
            if (className.equals("short"))
                cls = short.class;
            if (className.equals("int"))
                cls = int.class;
            if (className.equals("long"))
                cls = long.class;
            if (className.equals("char"))
                cls = char.class;
            if (className.equals("float"))
                cls = float.class;
            if (className.equals("double"))
                cls = double.class;
            if (className.equals("boolean"))
                cls = boolean.class;
            if (className.equals("void"))
                cls = void.class;
        }
        array = ArrayWork.create(cls, 3);
        System.out.println(ArrayWork.toString(array));
        System.out.println("Зміна розміру:");
        array = ArrayWork.change(cls, array, 5);
        System.out.println(ArrayWork.toString(array));
        //створення з іншого масиву. Зчитування зі строки опускаю, щоб не було багато однакового коду
        Double[] mas = new Double[]{1.0, 2.3, 3.5};
        array = ArrayWork.create(Double.class, 3, mas);
        System.out.println(ArrayWork.toString(array));
        System.out.println("Зміна розміру:");
        array = ArrayWork.change(Double.class, array, 5);
        System.out.println(ArrayWork.toString(array));
        array = ArrayWork.create(double.class, 3, mas);
        System.out.println(ArrayWork.toString(array));
        System.out.println("Зміна розміру:");
        array = ArrayWork.change(double.class, array, 10);
        System.out.println(ArrayWork.toString(array));
        array = ArrayWork.create(boolean.class, 3, 3);
        System.out.println(ArrayWork.toString(array));
        System.out.println("Зміна розміру:");
        array = ArrayWork.change(boolean.class, array, 4, 4);
        System.out.println(ArrayWork.toString(array));
    }

}

class ArrayWork {
    static Object create(Class cls, int len) {
        return Array.newInstance(cls, len);
    }

    static Object create(Class cls, int len, Object[] arr) {
        Object array = Array.newInstance(cls, len);
        for (int i = 0; i < len; i++) {
            Array.set(array, i, arr[i]);
        }
        return array;
    }

    static Object create(Class cls, int a, int b) {
        return Array.newInstance(cls, a, b);
    }

    static Object change(Class cls, Object arr, int len) {
        Object newArr = Array.newInstance(cls, len);
        System.arraycopy(arr, 0, newArr, 0, Array.getLength(arr));
        return newArr;
    }

    static Object change(Class cls, Object arr, int a, int b) {
        Object newArr = Array.newInstance(cls, a, b);
        for (int i = 0; i < Array.getLength(arr); i++) {
            Object array = Array.get(arr, i);
            System.arraycopy(array, 0, Array.get(newArr, i), 0, Array.getLength(array));
        }
        return newArr;
    }

    public static String toString(Object array) {
        int countDim = 0;
        if (array.getClass().isArray()) {
            String clsName = array.getClass().getName();
            countDim = 1 + clsName.lastIndexOf('[');
        }
        String string = "";
        if (countDim == 1) {
            String s = "{";
            for (int i = 0; i < Array.getLength(array) - 1; i++) {
                s += Array.get(array, i) + ", ";
            }
            s += Array.get(array, Array.getLength(array) - 1) + "}";
            string = array.getClass().getSimpleName().replace("]", Array.getLength(array) + "]") + " = " + s;
        } else if (countDim == 2) {
            String s = "{";
            for (int i = 0; i < Array.getLength(array) - 1; i++) {
                Object arr = Array.get(array, i);
                s += "{";
                for (int j = 0; j < Array.getLength(arr) - 1; j++) {
                    s += Array.get(arr, i) + ", ";
                }
                s += Array.get(arr, Array.getLength(arr) - 1) + "}, ";
            }
            s += "{";
            Object arr = Array.get(array, Array.getLength(array) - 1);
            for (int i = 0; i < Array.getLength(arr) - 1; i++) {
                s += Array.get(arr, i) + ", ";
            }
            s += Array.get(arr, Array.getLength(arr) - 1) + "}}";
            string = array.getClass().getSimpleName().replace("]", Array.getLength(arr) + "]") + " = " + s;
            string = string.replaceFirst("]", Array.getLength(array) + "]");
            int index = string.indexOf("[");
            string = string.substring(0, index + 1) + string.substring(index + 2, string.length() - 1);
            string += "}";
        }
        return string;
    }
}
