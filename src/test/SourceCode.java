package test;

import java.util.HashMap;
import java.util.Map;

public class SourceCode {

    public static void main(String[] args) {
//        mapHash();

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "2");
        map.put(3, "4");
        map.forEach((k, v) -> System.out.println(k + "----" + v));
        System.out.println("ok");
    }

    private static void mapHash() {
        int a = 1;
        String b = "cc";
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * hashMap 源码中关于key的计算
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
