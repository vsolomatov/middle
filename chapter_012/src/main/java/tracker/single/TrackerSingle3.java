package tracker.single;

/**
 * Данная реализация шаблона аналогична не многопоточной среде
 * Его работа стабильна и не влияет на производительность системы.
 */
public class TrackerSingle3 {

    private TrackerSingle3() {
    }

    public static TrackerSingle3 getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingle3 INSTANCE = new TrackerSingle3();
    }

    public static void main(String[] args) {
        TrackerSingle3 tracker = TrackerSingle3.getInstance();
    }
}
