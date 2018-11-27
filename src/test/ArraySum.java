package test;

public class ArraySum {

    /**
     * sum[i] 存储前i个元素和，sum[0] = 0，sum[i] = nums[0]+...+nums[i]
     */
    private int[] sum;

    /**
     * 在初始话求和数组时的时间复杂度为O(n)
     *
     * @param nums
     */
    public ArraySum(int[] nums) {
        this.sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i];
    }

    /**
     * 指定区间的和，此时的时间复杂度位 O(1)
     *
     * @param i
     * @param j
     * @return
     */
    public int sumRange(int i, int j) {
        return sum[j] - sum[i];
    }
}
