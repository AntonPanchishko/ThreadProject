import org.apache.log4j.Logger;

public class RunnableClass implements Runnable {
    private static final Logger logger = Logger.getLogger(RunnableClass.class);
    private final short COUNT_LENGTH = 100;
    private final Counter counter;

    public RunnableClass(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        logger.info("Runnable " + Thread.currentThread().getName() + " was started ");
        for (int i = 0; i <= COUNT_LENGTH; i++) {
                logger.info(Thread.currentThread().getName() + " | " + counter.getCount());
        }
        logger.info(Thread.currentThread().getName() + " was finished");
    }
}
