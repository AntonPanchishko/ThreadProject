import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyCallableForkJoinTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 20;
    private List<Integer> list;

    public MyCallableForkJoinTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        if (list.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        return processing(list);
    }

    private Collection<MyCallableForkJoinTask> createSubtasks() {
        List<MyCallableForkJoinTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new MyCallableForkJoinTask(list
                .subList(0, list.size() / 2)));
        dividedTasks.add(new MyCallableForkJoinTask(list
                .subList(list.size() / 2, list.size())));
        return dividedTasks;
    }

    private Integer processing(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    public List<Integer> getList() {
        return list;
    }
}
