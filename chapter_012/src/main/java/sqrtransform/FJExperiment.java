package sqrtransform;

import java.util.concurrent.ForkJoinPool;

import static java.util.concurrent.ForkJoinPool.getCommonPoolParallelism;

public class FJExperiment {
    public static void main(String[] args) {
        int pLevel;
        int threshold;

        if (args.length != 2) {
            System.out.println("Использование : FJExperiment параллелизм порог");
            return;
        }

        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        //Эти переменные исполь зуются для измерения
        // времени выполнения задачи
        long beginT, endT;

        // Создать пул задач.
        // Обратите внимание на установку уровня параллелизма
        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        double[] nums = new double[1000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) i;
        }

        Transform task = new Transform(nums, 0, nums.length, threshold);

        // начать измерение времени выполнения задачи
        beginT = System.nanoTime();

        // запустить главную задачу типа ForkJoinTask
        //fjp.invoke(task); // синхронно
        fjp.execute(task); // асинхронно

        // отобразить состояние пула во время ожидания
        while (!task.isDone()) {
            System.out.println(fjp);
        }

        // завершить измерение времени выполнения задачи
        endT = System.nanoTime();

        System.out.println("Уровень параллелизма : " + pLevel);
        System.out.println("    getCommonPoolParallelism() = " + getCommonPoolParallelism());
        System.out.println("    Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        System.out.println("Порог последовательной обработки : " + threshold);
        System.out.println("Истекшее время : " + (endT - beginT) + " нс");
        System.out.println();

    }
}
