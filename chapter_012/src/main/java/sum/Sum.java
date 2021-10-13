package sum;

import java.util.concurrent.RecursiveTask;

// Простой пример применения класса RecursiveTask<V>

// класс RecursiveTask, используемый для вычисления суммы
// значений элементов в массиве типа Double
public class Sum extends RecursiveTask<Double> {
    // Пороговое значение последовательного выполнения
    final int seqThresHold = 500;

    // Обрабатываемый массив
    double[] data;

    // определить часть обрабатываемых данных
    int start, end;

    Sum(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }
    // определить сумму значений элементов в массиве типа double

    protected Double compute() {
        double sum = 0;
        // Если количество элементов ниже порогового значения ,
        // то выполнить далее обработку последовательно
        if ((end - start) < seqThresHold) {
            // суммировать значения элементов в массиве типа double
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            // В противном случае продолжить разделение данных на
            // меньшие части
            // найти середину
            int middle = (start + end) / 2;
            // запустить новые подзадачи на выполнение, используя
            // разделенные на части данные
            Sum subTaskA = new Sum(data, start, middle);
            Sum subTaskB = new Sum(data, middle, end);
            // запустить каждую подзадачу путем разветвления
            subTaskA.fork();
            subTaskB.fork();
            // ожидать завершения подзадач и накопить результаты
            sum = subTaskA.join() + subTaskB.join();
        }
        // возвратить конечную сумму
        return sum;
    }
}
