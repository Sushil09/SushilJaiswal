package observers;


/* The purpose is to create update observer
 * via update method whenever any insert,update,delete happens
 */

public interface IObserver {
    void update(char type, String path, String name);
}
