package test;


import java.util.*;

/**
 * 数组中前k个频次最高的元素
 */
public class PriorityQueueTest {

    private static class Freq implements Comparable<Freq> {
        public Integer key, value;

        public Freq(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Freq another) {//使用频次进行排序
            if (this.value < another.value)
                return -1;
            else if (this.value > another.value)
                return 1;
            else
                return 0;
        }
    }

    /**
     * 根据频次排序
     *
     * @param data
     * @param k
     * @return
     */
    public static List<Integer> sortPower(int[] data, int k) {

        Map<Integer, Integer> map = new TreeMap<>();
        //采集信息
        for (int i = 0; i < data.length; i++) {
            if (map.containsKey(data[i]))
                map.put(data[i], map.get(data[i]) + 1);
            else
                map.put(data[i], 1);
        }

        //设置优先队列
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            } else if (map.get(key) > pq.peek().value) {
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }

        List<Integer> lis = new ArrayList<>();
        while (!pq.isEmpty()) {
            lis.add(pq.remove().key);
        }
        return lis;

    }

    public static void main(String[] args) {
        int[] data = {1, 1, 1, 2, 2, 4, 3, 3, 3, 5, 6, 6, 5, 7};
        int k = 5;
        List<Integer> list = sortPower(data, k);
        list.forEach(System.out::println);
    }
}
