package tracker.single;

/**
 * Инициализация и проверка экземпляра происходит внутри критической секции.
 * Этот шаблон деградирует производительность.
 * Использовать этот шаблон не рекомендуется.
 */
public class TrackerSingle2 {
    private static TrackerSingle2 instance;

    private TrackerSingle2() {
    }

    public static synchronized TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle2 tracker = TrackerSingle2.getInstance();
    }
}
