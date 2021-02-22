import org.apache.log4j.Logger;

public class ThreadClass extends Thread {
    private static final Logger logger = Logger.getLogger(RunnableClass.class);
    private static final int COUNT_LENGTH = 100;
    private final Counter counter;

    public ThreadClass(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        logger.info("Tread " + Thread.currentThread().getName() + " was started ");
        for (int i = 0; i <= COUNT_LENGTH; i++) {
            logger.info(Thread.currentThread().getName() + " | " + counter.getCount());
        }
        logger.info(Thread.currentThread().getName() + " was finished");
    }
}
