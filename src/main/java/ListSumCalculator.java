import java.util.List;
import java.util.concurrent.Callable;

public class ListSumCalculator implements Callable<Integer> {
    private List<Integer> list;

    public ListSumCalculator(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        return list.stream().reduce(0, Integer::sum);
    }
}
