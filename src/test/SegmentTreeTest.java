package test;

import tree.SegmentTree;

public class SegmentTreeTest {

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //       18
//           1-4  5-8
//        1-2 3-4 5-6 7-8
//       1  2  3  4  5  6  7  8

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (e, f) -> e + f);

        System.out.println(segmentTree.query(0, 3));
    }
}
