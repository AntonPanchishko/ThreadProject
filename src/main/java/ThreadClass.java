import org.apache.log4j.Logger;

public class ThreadClass extends Thread {
    private static final Logger logger = Logger.getLogger(RunnableClass.class);
    private static final int COUNT_VALUE = 100;
    private final Counter counter;

    public ThreadClass(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        logger.info("Thread " + Thread.currentThread().getName() + " was started ");

        while (counter.getCount() <= COUNT_VALUE) {
            int value = counter.getCount();

            logger.info(Thread.currentThread().getName() + " | " + value);
            counter.increment();
        }
        logger.info("Thread " + Thread.currentThread().getName() + " was finished");
    }
}
