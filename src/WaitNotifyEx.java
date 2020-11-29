public class WaitNotifyEx {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the production method ...");
            wait();
            System.out.println("Again in the production method ...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized(this) {
            System.out.println("Consume method is executed ...");
            notify();
        }
    }

    public static void main(String[] args) {
        WaitNotifyEx waitNotifyEx = new WaitNotifyEx();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitNotifyEx.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitNotifyEx.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
