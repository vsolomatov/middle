package cache;

public class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public void createInstance(String threadName) {
        System.out.println("threadName = " + threadName);
        Cache cache1 = instOf();
        System.out.println("    cache1 = " + cache1);
    }

    public Cache get() {
        return cache;
    }
}
