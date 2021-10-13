package single.lock.list;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();

        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));

        first.start();
        second.start();

        first.join();
        second.join();

        // Сформируем множество rsl
        Set<Integer> rsl = new TreeSet<>();
        // Получаем итератор
        Iterator<Integer> listIterator = list.iterator();
        // C помощью этого метода мы пробегаем по итератору, т.о. мы стоим в конце итератора, больше элементов нет
        listIterator.forEachRemaining(rsl::add);

        // Добавляем в список еще один элемент
        Thread third = new Thread(() -> list.add(3));
        third.start();
        third.join();
        rsl.add(3);

        // Получаем новый итератор
        listIterator = list.iterator();

        // Добавляем в список еще один элемент
        Thread fourth = new Thread(() -> list.add(4));
        fourth.start();
        fourth.join();
        rsl.add(4);

        // Выводим элементы нашего списка, информация о которых имеется в итераторе
        while (listIterator.hasNext()) {
            Integer item = listIterator.next();
            System.out.println("item = " + item);
        }

        Integer[] arrayRsl = {1, 2, 3, 4};
        Set<Integer> targetSet = new TreeSet<>();
        Collections.addAll(targetSet, arrayRsl);

        // Проверяем
        assertThat(rsl, is(targetSet));

        // Получаем новый итератор
        listIterator = list.iterator();
        // Должны быть выведены все 4 элемента
        while (listIterator.hasNext()) {
            Integer item = listIterator.next();
            System.out.println("** item = " + item);
        }
    }
}
