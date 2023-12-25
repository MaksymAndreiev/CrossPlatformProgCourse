package compute;

import java.io.Serializable;
import java.rmi.Remote;

public interface Task<T> extends Serializable, Remote {
    T execute();
}
