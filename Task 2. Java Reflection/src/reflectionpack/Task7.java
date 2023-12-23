package reflectionpack;
import com.google.gson.Gson;

public class Task7 {
    public static void main(String[] args) {
        Cat cat = new Cat("Abc", 1);
        System.out.println(cat.toString());
        Gson gson = new Gson();
        String save = gson.toJson(cat);
        System.out.println(save);
        Cat restored = gson.fromJson(save, Cat.class);
        System.out.println(restored.toString());
    }
}

class Cat {

    String name;
    int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Кіт " + name + ", вік " + age;
    }
}