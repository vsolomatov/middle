package row.col.sum;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {


    //Эти переменные исполь зуются для измерения
    // времени выполнения задачи
    private static long beginT, endT;

    public static class Sums {
        private int rowSum;
        private int colSum;

        //////////////////////
        // Getters and Setters
        //////////////////////
        public int getRowSum() {
            return rowSum;
        }
        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }
        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        // начать измерение времени выполнения задачи
        beginT = System.nanoTime();

        int n = matrix.length;
        Sums[] sums = new Sums[n];

        for (int i = 0; i < n; i++) {
            Sums sum = new Sums();
            int intSum = 0;
            for (int col = 0; col < n; col++) {
                intSum += matrix[i][col];
            }
            sum.setRowSum(intSum);
            intSum = 0;
            for (int[] ints : matrix) {
                intSum += ints[i];
            }
            sum.setColSum(intSum);
            sums[i] = sum;
        }
        // завершить измерение времени выполнения задачи
        endT = System.nanoTime();
        System.out.println("    Истекшее время : " + (endT - beginT) / 1000000 + " мс");
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        // начать измерение времени выполнения задачи
        beginT = System.nanoTime();

        int n = matrix.length;
        Sums[] sums = new Sums[n];

        for (int i = 0; i < n; i++) {
            try {
                sums[i] = getTask(matrix, i).get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Exception for i = " + i);
                e.printStackTrace();
            }
        }
        // завершить измерение времени выполнения задачи
        endT = System.nanoTime();
        System.out.println("    Истекшее время : " + (endT - beginT) / 1000000 + " мс");
        return sums;
    }

    /**
     * Задача считает сумму по i-му столбцу и по i-ой строке
     *      (запускается обновременно с созданием)
     * @param data - исходная матрица
     * @param i - номер строки и столбца
     * @return - объект задачи
     */
    public static CompletableFuture<Sums> getTask(int[][] data, int i) {
        return CompletableFuture.supplyAsync(() -> {
            int n = data.length;
            Sums sums = new Sums();
            int sum = 0;
            for (int col = 0; col < n; col++) {
                sum += data[i][col];
            }
            sums.setRowSum(sum);
            sum = 0;
            for (int[] datum : data) {
                sum += datum[i];
            }
            sums.setColSum(sum);
            return sums;
        });
    }

}
