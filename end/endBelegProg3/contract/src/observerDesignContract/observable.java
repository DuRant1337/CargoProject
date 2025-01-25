package observerDesignContract;

import java.util.Observer;

public interface observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
