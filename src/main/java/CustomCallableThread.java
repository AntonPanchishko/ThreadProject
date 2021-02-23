import java.util.List;
import java.util.concurrent.Callable;

public class CustomCallableThread implements Callable<Integer> {
    private List<Integer> list;

    public CustomCallableThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() throws Exception {
        return list.stream().reduce(0, Integer::sum);
    }
}
