package reflectionpack;

import java.lang.reflect.*;

public class Task5 {
    public static void main(String[] args) {

        NumFunction fun = new NumFunction();
        Class<?> proxy = Proxy.getProxyClass(fun.getClass().getClassLoader(),
                fun.getClass().getInterfaces());

        System.out.println("F1: " + fun.evalf(1.0));
        try {
            Evaluatable e = (Evaluatable) proxy.getConstructor(InvocationHandler.class).newInstance(new MyProxy(fun));
            e.evalf(1.0);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        Evaluatable e = (Evaluatable) Proxy.newProxyInstance(fun.getClass().getClassLoader(),
                fun.getClass().getInterfaces(), new MyProxy(fun));
        e.evalf(1.0);
        fun.evalf(1.0);
    }
}

interface Evaluatable {
    double evalf(double x);
}

class NumFunction implements Evaluatable {
    @Override
    public double evalf(double x) {
        return Math.sin(x) / x;
    }
}

class MyProxy implements InvocationHandler {
    private Object obj;

    public MyProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for (Object a : args)
            System.out.println("x: " + a);
        double start = System.nanoTime();
        double res = (double) method.invoke(obj, args);
        double finish = System.nanoTime();
        System.out.println("[sin(x)/x] took " + (finish - start) + " ns");
        for (Object a : args)
            System.out.println("[sin(x)/x]." + method.getName() + "(" + a + ")" + " = " + res);
        return res;
    }
}