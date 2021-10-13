package async.sum;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class AsyncSumTest {

    @Test
    public void whenAsyncSum() {
        int[][] matrix = new int[4][4];
        System.out.println("matrix.length = " + matrix.length);
        System.out.println("Исходный массив:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int rnd = -100 + (int) (Math.random() * (((100 + 100) - 1)) * 100) / 100;
                matrix[i][j] = rnd;
                System.out.println("matrix[" + i + "," + j + "] = " + matrix[i][j]);
            }
        }

        try {
            int[] results = AsyncSum.asyncSum(matrix);
            System.out.println("Полученные суммы:");
            for (int i = 0; i < results.length; i++) {
                System.out.println("results[" + i + "] = " + results[i]);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
