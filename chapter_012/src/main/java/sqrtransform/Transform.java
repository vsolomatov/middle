package sqrtransform;

import java.util.concurrent.RecursiveAction;

public class Transform extends RecursiveAction {
    // Порог последовательного выполнения, устанавливаемый конструктором
    int seqThreshold;
    // обрабатываемый массив
    double[] data;
    // определить часть обрабатываемых данных
    int start, end;

    Transform(double[] vals, int s, int e, int t) {
        data = vals;
        start = s;
        end = e;
        seqThreshold = t;
    }

    // Этот метод выполняет параллельное вычисление
    protected void compute() {
        // Если количество элементов меньше порогового значения,
        // выполнить дальнейшую обработку последовательно
        if ((end - start) < seqThreshold) {
            //В следующем фрагменте кода элементу по четному
            // индексу присваивается квадратный корень его
            // первоначального значения, а элементу по нечетному
            // индексу - кубический корень его первоначального значения.
            // Этот код предназначен только для потребления времени ЦП,
            // чтобы сделать нагляднее эффект от параллельного выполнения
            for (int i = start; i < end; i++) {
                if ((data[i] % 2) == 0) {
                    data[i] = Math.sqrt(data[i]);
                } else {
                    data[i] = Math.cbrt(data[i]);
                }
            }
        } else {
            // в противном случае продолжить разделение данных на
            // меньшие части

            // найти середину
            int middle = (start + end) / 2;
            // запустить новые подзадачи на выполнение , используя
            // разделенные на части данные
            invokeAll(new Transform(data, start, middle, seqThreshold), new Transform(data, middle, end, seqThreshold));
        }
    }
}
