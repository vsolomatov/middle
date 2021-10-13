package row.col.sum;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RowColSumTest {

    @Test
    public void whenSum() {
        int[][] matrix = new int[10_000][10_000];
        System.out.println("matrix.length = " + matrix.length);
        //System.out.println("Исходный массив:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int rnd = -100 + (int) (Math.random() * (((100 + 100) - 1)) * 100) / 100;
                matrix[i][j] = rnd;
                //System.out.println("matrix[" + i + "," + j + "] = " + matrix[i][j]);
            }
        }

        System.out.println("Запускаем RowColSum.asyncSum()");
        RowColSum.Sums[] resultsAsync = RowColSum.asyncSum(matrix);
        System.out.println("Часть полученных сумм методом asyncSum:");
        for (int i = 0; i < 10; i++) {
            System.out.println("    resultsAsync[" + i + "] = " + resultsAsync[i]);
        }

        System.out.println("Запускаем RowColSum.sum()");
        RowColSum.Sums[] results = RowColSum.sum(matrix);
        System.out.println("Часть полученных сумм методом sum:");
        for (int i = 0; i < 10; i++) {
            System.out.println("    results[" + i + "] = " + results[i]);
        }

        for (int i = 0; i < results.length; i++) {
            assertThat(resultsAsync[i], equalTo(results[i]));
        }

    }
}
