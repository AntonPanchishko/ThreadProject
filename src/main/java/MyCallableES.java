import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;

public class MyCallableES implements Callable<Integer> {
    private static final int THRESHOLD = 20;
    private List<Integer> list;

    public MyCallableES(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() {
        List<List<Integer>> lists = ListUtils.partition(list,list.size() / THRESHOLD);
        ExecutorService executorService = Executors.newFixedThreadPool(THRESHOLD);

        List<CustomCallableThread> callableList = lists.stream()
                .map(CustomCallableThread::new)
                .collect(Collectors.toList());
        try {
            executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't invoke this threads " + e);
        }
        int result = getSum(executorService, callableList);
        executorService.shutdown();
        return result;
    }

    private Integer getSum(ExecutorService executorService,
                           List<CustomCallableThread> callableList) {
        int result = 0;
        for (int i = 0; i < callableList.size(); i++) {
            try {
                result = result + executorService.submit(callableList.get(i)).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Can't submit this thread " + e);
            }
        }
        return result;
    }

    public List<Integer> getList() {
        return list;
    }
}
