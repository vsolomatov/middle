package parallel.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Класс RecursiveTask, используемый для получения списка индексов элементов эквивалентных искомому элементу
  */
public class Search<T> extends RecursiveTask<List<Integer>> {
    // Пороговое значение последовательного выполнения
    final int seqThresHold = 10;

    // Обрабатываемый массив
    T[] data;

    // определить часть обрабатываемых данных
    int start, end;

    // Искомый элемент массива
    T t;

    Search(T[] vals, int s, int e, T element) {
        data = vals;
        start = s;
        end = e;
        t = element;
    }

    // Получает список индексов определенного элемента в массиве
    protected List<Integer> compute() {
        List<Integer> indexes = new ArrayList<>();
        // Если количество элементов ниже порогового значения ,
        // то выполнить далее обработку последовательно
        if ((end - start) < seqThresHold) {
            // проверить значения элементов в массиве и вернуть список индексов, если найдем эквивалентные элементы
            for (int i = start; i < end; i++) {
                if (data[i].equals(t)) {
                    indexes.add(i);
                    //System.out.println("indexes = " + indexes);
                }
            }
        } else {
            // В противном случае продолжить разделение данных на меньшие части,
            // найти середину
            int middle = (start + end) / 2;

            // запустить новые подзадачи на выполнение, используя разделенные на части данные
            Search<T> subTaskA = new Search<>(data, start, middle, t);
            Search<T> subTaskB = new Search<>(data, middle, end, t);

            // запустить каждую подзадачу путем разветвления
            subTaskA.fork();
            subTaskB.fork();

            // ожидать завершения подзадач и соединить результаты
            indexes.addAll(subTaskA.join());
            indexes.addAll(subTaskB.join());
        }
        // возвратить список найденных индексов
        return indexes;
    }
}
