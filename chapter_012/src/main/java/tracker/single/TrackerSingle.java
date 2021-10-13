package tracker.single;

/**
 * Объект enum создается при загрузки класса и безопасно публикуется всем клиентам
 */
public enum TrackerSingle {
    INSTANCE;

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.INSTANCE;
    }
}
