import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;

public class SumCalculatorES {
    private static final int THRESHOLD = 20;
    private List<Integer> list;

    public SumCalculatorES(List<Integer> list) {
        this.list = list;
    }

    public Integer calculateSum() {
        List<List<Integer>> lists = ListUtils.partition(list, list.size() / THRESHOLD);
        ExecutorService executorService = Executors.newFixedThreadPool(THRESHOLD);

        List<Callable<Integer>> callableList = lists.stream()
                .map(ListSumCalculator::new)
                .collect(Collectors.toList());
        List<Future<Integer>> futures;
        try {
            futures = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't invoke this threads " + e);
        }
        int result = getSum(futures);
        executorService.shutdown();
        return result;
    }

    private Integer getSum(List<Future<Integer>> futures) {
        int result = 0;
        try {
            for (int i = 0; i < futures.size(); i++) {
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
