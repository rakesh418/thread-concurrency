public class ThreadSyncEx1 {
    public static int count=0;

    public  static synchronized void counter(){
        count++;
    }

    public static  void process() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100000;i++){
                    counter();
//                    System.out.println(Thread.currentThread().getName()+" : "+count);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100000;i++){
                    counter();
//                    System.out.println(Thread.currentThread().getName()+" : "+count);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("The counter is :"+ count );
    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }
}
