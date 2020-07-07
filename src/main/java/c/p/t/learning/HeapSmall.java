package c.p.t.learning;

/**
 * @ClassName HeapSmall
 * @Description TODO 小顶堆
 * @Author User
 * @DATE 2020/4/27 14:04
 * @Version 1.0
 **/
public class HeapSmall {

    void buildMaxSmall(int[] array,int len,int root){
        int small = root;
        int l = 2*root+1;
        int r = 2*root+2;
        if(l<len&&array[small]>array[l]){
            small = l;
        }
        if(r<len&&array[small]>array[r]){
            small = r;
        }
        if (small != root) {
            int temp = array[small];
            array[small] = array[root];
            array[root] = temp;

            //再次检查是否符合大顶堆
            buildMaxSmall(array, len,small);
        }
    }

    void swap(int[] array,int target,int dest){
        int temp = array[target];
        array[target] = array[dest];
        array[dest] = temp;
    }

    public static void main(String[] args) {
        HeapSmall ht = new HeapSmall();
        int[] array = new int[]{12, 43, 5, 23, 11, 100, 34};
        int len = array.length;

        //step1:根节点为最大值
        for (int i = len / 2 - 1; i >= 0; i--) { // len/2-1 : 最后一个非叶子节点下标
            ht.buildMaxSmall(array, len,i);
        }
        //step2:根节点与最后一个非叶子节点对调
        for (int i = len - 1; i >= 0; i--) {
            if(array[i]>array[0]){
                ht.swap(array,i,0);
                //重构大顶堆
            }
            ht.buildMaxSmall(array,i,0);
        }

        for(int i:array){
            System.out.println(i);
        }

    }

}
