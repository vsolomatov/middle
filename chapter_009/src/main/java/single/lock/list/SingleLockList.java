package single.lock.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    // Я не нашел другого динамического списка, который я делал на предыдущих уровнях в 2018 году, поэтому взял этот
    @GuardedBy("this")
    private final SimpleLinked<T> list = new SimpleLinked<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }


    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    synchronized private SimpleLinked<T> copy(SimpleLinked<T> resource) {
        System.out.println("Started SingleLockList.copy");
        SimpleLinked<T> result = new SimpleLinked<>();
        for (Iterator<T> it = resource.iterator(); it.hasNext();) {
            T item = it.next();
            result.add(item);
        }
        System.out.println("Finished SingleLockList.copy");
        return result;
    }
}
