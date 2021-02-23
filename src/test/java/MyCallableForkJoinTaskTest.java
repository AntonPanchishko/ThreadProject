import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class MyCallableForkJoinTaskTest {
    private MyCallableForkJoinTask myCallableForkJoinTask = new MyCallableForkJoinTask(Util.generaleRandomList());
    private MyCallableES myCallableES = new MyCallableES(Util.generaleRandomList());

    @Test
    void callByExecutorService() throws Exception {
        Integer integer = myCallableES.call();
        Integer value = Util.calculateSumValue(myCallableES.getList());
        assertEquals(value, integer);
    }

    @Test
    void callByForkJoinPool() throws Exception {
        Integer integer = myCallableForkJoinTask.compute();
        Integer value = Util.calculateSumValue(myCallableForkJoinTask.getList());
        assertEquals(value, integer);
    }
}

class Util {
    private final static int LIST_RANGE = 1000000;
    private final static int VALUE_RANGE = 10;

    public static List<Integer> generaleRandomList() {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < LIST_RANGE; i++) {
            list.add((int) (Math.random() * VALUE_RANGE));
        }
        return list;
    }

    public static Integer calculateSumValue(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}