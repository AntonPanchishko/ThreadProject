import java.util.ArrayList;
import java.util.List;

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