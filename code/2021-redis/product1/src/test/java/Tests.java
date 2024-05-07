
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


class Tests {

    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {
          CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
              try {
                  Thread.sleep(100L);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              return "sss";
        });

        completableFuture2.thenAccept((res)->{
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"2");

        }).thenRun(()->{
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"3");
        });
        Thread.sleep(300L);
        System.out.println("over");
    }


    @Test
    public void aa() throws ExecutionException, InterruptedException {
        List<String> list = new ArrayList<>();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            list.add("s1");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        });
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            list.add("s2");
            return "s2";
        });
        CompletableFuture.allOf(completableFuture,completableFuture1).join();
        list.forEach(System.out::println);

    }

    public String get(){
        String s= "1";
        try {

            s= "2";
            return s;
        }catch (Exception e){

        }finally {
            System.out.println("finally");
//            return "3";
        }
        return "4";

    }
}
