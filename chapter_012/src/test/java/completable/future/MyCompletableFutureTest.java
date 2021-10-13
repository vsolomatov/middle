package completable.future;

import org.junit.Test;

public class MyCompletableFutureTest {

    @Test
    public void whenRunAsyncExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenRunAsyncExample");
        MyCompletableFuture.runAsyncExample();
        System.out.println("Finished MyCompletableFutureTest.whenRunAsyncExample");
        System.out.println();
    }

    @Test
    public void whenSupplyAsyncExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenSupplyAsyncExample");
        MyCompletableFuture.supplyAsyncExample();
        System.out.println("Finished MyCompletableFutureTest.whenSupplyAsyncExample");
        System.out.println();
    }

    @Test
    public void whenRunExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenRunExample");
        MyCompletableFuture.thenRunExample();
        System.out.println("Finished MyCompletableFutureTest.whenRunExample");
        System.out.println();
    }

    @Test
    public void whenAcceptExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenAcceptExample");
        MyCompletableFuture.thenAcceptExample();
        System.out.println("Finished MyCompletableFutureTest.whenAcceptExample");
        System.out.println();
    }

    @Test
    public void whenApplyExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenApplyExample");
        MyCompletableFuture.thenApplyExample();
        System.out.println("Finished MyCompletableFutureTest.whenApplyExample");
        System.out.println();
    }

    @Test
    public void whenComposeExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenComposeExample");
        MyCompletableFuture.thenComposeExample();
        System.out.println("Finished MyCompletableFutureTest.whenComposeExample");
        System.out.println();
    }

    @Test
    public void whenCombineExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenCombineExample");
        MyCompletableFuture.thenCombineExample();
        System.out.println("Finished MyCompletableFutureTest.whenCombineExample");
        System.out.println();
    }

    @Test
    public void whenAllOfExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenAllOfExample");
        MyCompletableFuture.allOfExample();
        System.out.println("Finished MyCompletableFutureTest.whenAllOfExample");
        System.out.println();
    }

    @Test
    public void whenAnyOfExample() throws Exception {
        System.out.println("Started MyCompletableFutureTest.whenAnyOfExample");
        MyCompletableFuture.anyOfExample();
        System.out.println("Finished MyCompletableFutureTest.whenAnyOfExample");
        System.out.println();
    }


}
