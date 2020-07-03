package c.p.t.leetcode;

/**
 * @ClassName FindMedianSortedArrays4
 * @Description TODO 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * <p>
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 * @Author User
 * @DATE 2020/7/1 10:49
 * @Version 1.0
 **/
public class FindMedianSortedArrays4 {

    public static void main(String[] args) {
        FindMedianSortedArrays4 findMedianSortedArrays4 = new FindMedianSortedArrays4();
        int[] a1 = new int[]{1, 3, 15, 39};
        int[] a2 = new int[]{2, 4, 7, 9, 23, 45, 78};
            // 1, 3, 15, 39     2, |4|, 7, 9, 23,45,78
        System.out.println(findMedianSortedArrays4.findMedianSortedArrays(a1, a2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1>n2)
            return findMedianSortedArrays(nums2, nums1);
        int k = (n1 + n2 + 1)/2;
        int left = 0;
        int right = n1;
        while(left < right){
            int m1 = left +(right - left)/2;
            int m2 = k - m1;
            int comp1 = nums1[m1];
            int comp2 = nums2[m2-1];
            if (comp1 < comp2)
                left = m1 + 1;
            else
                right = m1;
        }
        int m1 = left;
        int m2 = k - left;
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1-1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2-1]);
        if ((n1 + n2) % 2 == 1)
            return c1;
        int c2 = Math.min( m1 >= n1 ? Integer.MAX_VALUE :nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return (c1 + c2) * 0.5;
    }

}
