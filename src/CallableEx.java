import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class ProcessorCall implements Callable<String>{

    private int id;

    public ProcessorCall(int id){
        this.id=id;
    }

    @Override
    public String call() throws Exception {
        if(id==3){
            Thread.sleep(10000);
        }
        System.out.println(Thread.currentThread().getName());
        return "Id : "+id;
    }
}

public class CallableEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();
        for(int i=1;i<=5;i++){
            Future<String> future = executor.submit(new ProcessorCall(i));
            futureList.add(future);
        }

        for(Future<String> future : futureList){
            System.out.println(future.get());
        }

        executor.shutdown();
    }
}
