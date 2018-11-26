package test;

import array.Array;

public class ArrayTest {

    public static void main(String[] args){
//        int [] socres = new int[] {100,99,66};
//        Arrays.stream(socres).forEach(System.out :: println);

        Array<Integer> array = new Array<>(20);

        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array);
        array.removeLast();
        System.out.println(array);

    }
}
