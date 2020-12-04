import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerCountDown implements Runnable{
    private int id;
    private CountDownLatch countDownLatch;

    public WorkerCountDown(int id, CountDownLatch countDownLatch){
        this.id=id;
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    public void doWork(){
        System.out.println("Thread with id "+id+"starts working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class CountDownLatchEx {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i=1; i<=5; i++){
            executor.execute(new WorkerCountDown(i,countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed all task");
    }
}
