package user.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User {
    private int id;

    @GuardedBy("this")
    private int amount;

    public User(int amount) {
        this.amount = amount;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public synchronized int getAmount() {
        return amount;
    }
    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public synchronized String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
}
