package tracker.single;

/**
 * С помощью поля final
 */
public class TrackerSingle1 {
    private static final TrackerSingle1 INSTANCE = new TrackerSingle1();

    private TrackerSingle1() {
    }

    public static TrackerSingle1 getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle1 tracker = TrackerSingle1.getInstance();
    }
}
