/**
 * Interface representing observable
 * @author xjalak00, xkvasn14
 */

package common;

/**
 * Interface representing observable
 */
public interface Observable {
    void addObserver(Observer var1);

    void removeObserver(Observer var1);

    void notifyObservers();

    public interface Observer {
        void update(Observable var1);
    }
}