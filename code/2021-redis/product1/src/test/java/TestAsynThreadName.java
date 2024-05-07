

import java.util.concurrent.CompletableFuture;

public class TestAsynThreadName {
    public static void main(String[] args) {
        new TestAsynThreadName().testThenApply2();
    }

    public void testThenApply2() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            info("====future=====");
            return 2;
        });
        future.thenApply(res -> {
            info("====future=====");
            return res + 1;
        }).thenApply(res -> {
            info("====future=====");
            return res + 1;
        }).thenAccept(res -> System.out.println(res));

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            info("====future2=====");
            return 2;
        });
        future2.thenApplyAsync(res -> {
            info("====future2=====");
            return res + 1;
        }).thenApplyAsync(res -> {
            info("====future2=====");
            return res + 1;
        }).thenAccept(res -> System.out.println(res));
    }

    public  void info(String mes) {
        String name = Thread.currentThread().getName();
        System.out.println("[ " + name + " ]: " + mes);
    }
}
