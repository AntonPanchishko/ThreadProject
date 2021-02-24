import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class MyCallableForkJoinTaskTest {
    private MyCallableForkJoinTask myCallableForkJoinTask =
            new MyCallableForkJoinTask(Util.generaleRandomList());
    private MyCallableES myCallableES = new MyCallableES(Util.generaleRandomList());

    @Test
    void callByExecutorService() throws Exception {
        Integer integer = myCallableES.calculateSum();
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


