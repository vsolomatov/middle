package user.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class UserStorage {
    private final List<User> users = new CopyOnWriteArrayList<>();

    @GuardedBy("this")
    private final AtomicInteger id = new AtomicInteger();

    public synchronized AtomicInteger getId() {
        return id;
    }


    public synchronized boolean add(User user) {
        user.setId(id.incrementAndGet());
        return users.add(user);
    }

    public synchronized boolean update(User user) {
        return true;
    }

    public synchronized boolean delete(User user) {
        return users.removeIf(user::equals);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        User user1 = findById(fromId);
        int user1Amount = user1.getAmount();
        if (user1Amount - amount < 0) {
            System.out.println("Ошибка перевода! Текущий остаток (" + user1Amount + ") меньше требуемой суммы: " + amount + ", для " + user1);
            result = false;
        } else {
            user1.setAmount(user1Amount - amount);
            User user2 = findById(toId);
            user2.setAmount(user2.getAmount() + amount);
        }
        return result;
    }


    public synchronized User findById(int id) {
        User result = null;
        for (User user : users) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    public synchronized List<User> findAll() {
        return users;
    }

}
