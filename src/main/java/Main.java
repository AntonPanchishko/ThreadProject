public class Main {
    public static void main(String[] args) {
        Counter counterForRunnable = new Counter();
        Counter counterForThread = new Counter();
        RunnableClass runnableClass = new RunnableClass(counterForRunnable);
        ThreadClass threadClass = new ThreadClass(counterForThread);
        new Thread(runnableClass).start();
        threadClass.run();
    }
}
