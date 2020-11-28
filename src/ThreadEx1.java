class Runner1 implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println("Runner 1 : "+i);
        }
    }
}

class Runner2 implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println("Runner 2 : "+i);
        }
    }
}

public class ThreadEx1 {
    public static void main(String[] args) throws InterruptedException {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        Thread t1 = new Thread(runner1);
        Thread t2 = new Thread(runner2);
        t1.start();
        t2.join();
        t2.start();
    }
}
