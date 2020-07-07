package c.p.t.learning;


import java.util.LinkedList;

/**
 * @ClassName HeapTop
 * @Description TODO 大顶堆：根节点值大于左右子节点的值，根节点的值一定是最大值；将根节点的值与最后一个位置(最底层的左节点或右节点)的节点交换位置
 * @Author User
 * @DATE 2020/4/26 13:55
 * @Version 1.0
 **/
public class HeapTop {

    void print(int... array) {
        StringBuilder str = new StringBuilder("结果：");
        for (int i : array) {
            str.append(i + ",");
        }
        System.out.println(str.substring(0, str.length() - 1));
    }

    /**
     * @param array 数组
     * @param len   完全二叉树的个数
     */
    void buildMaxTop(int[] array, int len) {

        //step1:根节点为最大值
        for (int i = len / 2 - 1; i >= 0; i--) { // len/2-1 : 最后一个非叶子节点下标
            int l = i * 2 + 1; //左叶子节点
            int r = i * 2 + 2; //右叶子节点
            int max = i;
            if (l < len && array[max] < array[l]) { //根节点小于左叶子节点
                max = l;
            }
            if (r < len && array[max] < array[r]) { //根节点小于右叶子节点
                max = r;
            }
            if (max != i) {
                swap(array,max,i);
                //判断叶子节点是否还有子节点，如果有并且不符合大顶堆特典，则需要重构大顶堆
                if ((2 * max + 1 < len && array[2 * max + 1] > array[max]) || (2 * max + 2 < len && array[2 * max + 2] > array[max])) {
                    buildMaxTop(array, len);
                }
            }
        }
    }

    /**
     * 根节点与最底层节点交换
     *
     * @param array
     * @param target 目标
     * @param dest   目的地
     */
    void swap(int[] array, int target, int dest) {
        int temp = array[target];
        array[target] = array[dest];
        array[dest] = temp;
    }

    public static void main(String[] args) {
        HeapTop ht = new HeapTop();
        int[] array = new int[]{12, 100, 5, 23, 11, 43, 34};
        int len = array.length;

        LinkedList<Integer> maxArray = new LinkedList<Integer>();
        for (int i = len; i > 0; i--) {
            ht.buildMaxTop(array, i);
            ht.print(array);
            ht.swap(array, i - 1, 0);
        }
        ht.print(array);
    }


}
