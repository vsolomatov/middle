package non.bloking.cache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Integer, Base> concurrentHashMap = new ConcurrentHashMap<>();

    public void add(Base model) {
        concurrentHashMap.putIfAbsent(model.getId(), model);
    }

    public Base update(Base model) throws OptimisticException {
        return concurrentHashMap.computeIfPresent(model.getId(), (integer, base) -> {
            if (base.getVersion() != model.getVersion()) {
                throw new OptimisticException("Выброшено исключение OptimisticException");
            }
            model.setVersion(model.getVersion() + 1);
            return model;
        });
    }

    public void delete(Base model) {
            concurrentHashMap.remove(model.getId());
    }

    public int size() {
        return concurrentHashMap.size();
    }

    @Override
    public String toString() {
        return "Cache{"
                + "concurrentHashMap=" + concurrentHashMap
                + '}';
    }
}
