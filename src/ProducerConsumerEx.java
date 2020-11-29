import java.util.ArrayList;
import java.util.List;

class Processor{
    private List<Integer> list = new ArrayList<>();
    private static final int upperLimit =5;
    private static final int lowerLimit =0;
    private final Object lock = new Object();
    private static int count=0;

    public void producer() throws InterruptedException{
        synchronized (lock) {
            while (true) {
                if (list.size() == upperLimit) {
                    System.out.println("Producer stopped because of upper limit");
                    lock.wait();
                } else {
                    list.add(count);
                    System.out.println("Producer kicked in : " + count);
                    count++;
                    lock.notify();
                }
                lock.wait();
//                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == lowerLimit) {
                    System.out.println("Consumer waiting for producer");
                    lock.wait();
                } else {
                    System.out.println("consumer kicked in " + list.get(0));
                    list.remove(0);
                    lock.notify();
                }
//                Thread.sleep(500);
            }
        }
    }
}

public class ProducerConsumerEx {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();
        consumerThread.start();
    }
}
