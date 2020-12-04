import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable{

    int id;
    public Task(int id){
        this.id=id;
    }

    @Override
    public void run() {
        System.out.println("Task id "+id+ "running with thread "+Thread.currentThread());
    }
}

public class SingleThreadExecutorEx {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++){
            executor.execute(new Task(i));
        }
    }
}
