package sqrtransform;

import java.util.concurrent.RecursiveAction;

public class SqrtTransform extends RecursiveAction {
    // В данном примере пороговое значение произвольно устанавливается
    // равным 1000 . В реальном коде его оптимальное значение может
    // быть определено в результате профилирования исполняющей системы
    // или экспериментально
    final int seqThreshold = 1000;
    // обрабатываемый массив
    double[] data;
    // определить часть обрабатываемых данных
    int start, end;

    SqrtTransform(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    // Этот метод выполняет параллельное вычисление
    protected void compute() {
        // Если количество элементов меньше порогового значения,
        // выполнить дальнейшую обработку последовательно
        if ((end - start) < seqThreshold) {

            // преобразовать значение каждого элемента массива
            // в его квадратный корень
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            // в противном случае продолжить разделение данных на
            // меньшие части

            // найти середину
            int middle = (start + end) / 2;
            // запустить новые подзадачи на выполнение , используя
            // разделенные на части данные
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));
        }
    }
}
