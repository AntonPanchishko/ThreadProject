public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        RunnableClass runnableClass = new RunnableClass(counter);
        ThreadClass threadClass = new ThreadClass(counter);
        new Thread(runnableClass).start();
        threadClass.run();
    }
}
