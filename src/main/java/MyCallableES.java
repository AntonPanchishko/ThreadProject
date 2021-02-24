import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;

public class MyCallableES {
    private static final int THRESHOLD = 20;
    private List<Integer> list;

    public MyCallableES(List<Integer> list) {
        this.list = list;
    }

    public Integer calculateSum() {
        List<List<Integer>> lists = ListUtils.partition(list, list.size() / THRESHOLD);
        ExecutorService executorService = Executors.newFixedThreadPool(THRESHOLD);

        List<MyCallable> callableList = lists.stream()
                .map(MyCallable::new)
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
                           List<MyCallable> callableList) {
        int result = 0;
        try {
            List<Future<Integer>> futures = executorService.invokeAll(callableList);
            for (int i = 0; i < callableList.size(); i++) {
                result = result + futures.get(i).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Can't submit this thread " + e);
        }
        return result;
    }

    public List<Integer> getList() {
        return list;
    }
}
