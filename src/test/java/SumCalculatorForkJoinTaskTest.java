import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SumCalculatorForkJoinTaskTest {
    private SumCalculatorForkJoinTask sumCalculatorForkJoinTask =
            new SumCalculatorForkJoinTask(ListSupplier.generaleRandomList());
    private SumCalculatorES sumCalculatorES = new SumCalculatorES(ListSupplier.generaleRandomList());

    @Test
    public void callByExecutorService() throws Exception {
        Integer integer = sumCalculatorES.calculateSum();
        Integer value = ListSupplier.calculateSumValue(sumCalculatorES.getList());
        assertEquals(value, integer);
    }

    @Test
    public void callByForkJoinPool() throws Exception {
        Integer integer = sumCalculatorForkJoinTask.compute();
        Integer value = ListSupplier.calculateSumValue(sumCalculatorForkJoinTask.getList());
        assertEquals(value, integer);
    }
}
