package udpWork;

import java.io.Serializable;
import java.util.ArrayList;

public class ActiveUsers implements Serializable {
    private ArrayList<User> users = new ArrayList<>();

    public ActiveUsers() {
    }

    public void add(User user){
        users.add(user);
    }

    public boolean isEmpty(){
        return users.isEmpty();
    }

    public int size(){
        return users.size();
    }

    public boolean contains(User user){
        return users.contains(user);
    }

    public User get(int index) {
        return users.get(index);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (User user :
                users) {
            buf.append(user + "\n");
        }
        return buf.toString();
    }
}
