package parallel.search;

import org.junit.Test;
import pc.ProducerInteger;
import pc.SimpleBlockingQueue;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest {

    @Test
    public void whenSearchString555() {
        // Создать пул задач
        ForkJoinPool fjp = new ForkJoinPool();

        // Размер исходного массива
        int lenArray = 500_000;

        // Объект для создания рандомных чисел (из главы 10)
        ProducerInteger producerInteger = new ProducerInteger(new SimpleBlockingQueue<>(1));

        // Инициализируем массив strings разными числами, преобразованными в строки
        // 555-элемент всегда делаем равным 555
        String[] strings = new String[lenArray];
        for (int i = 0; i < strings.length; i++) {
            int num = producerInteger.getRandomNumber(0, 100_000);
            if (i == 555) {
                strings[i] = "555";
            } else {
                strings[i] = Integer.toString(num);
            }
            //System.out.println("strings[" + i + "] = " + strings[i]);
        }

        Search<String> task = new Search<>(strings, 0, strings.length, "555");

        // Запустить задачу типа RecursiveTask<String> и ждать окончания
        List<Integer> indexes = fjp.invoke(task);

        System.out.println("Найденные индексы:");
        for (Integer index: indexes) {
            System.out.println("    index = " + index);
        }
        assertThat(indexes.contains(555), is(true));
    }
}
