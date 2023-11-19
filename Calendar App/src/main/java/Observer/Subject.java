package Observer;

public interface Subject {
    
    public void registerObserver (Observer observer);
    public void notifyObservers ();
}