package c.p.t.leetcode;

/**
 * @ClassName AddTwoNumbers2
 * @Description TODO 两数相加
 *          给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *          如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *          您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * @Author User
 * @DATE 2020/6/30 14:49
 * @Version 1.0
 **/
public class AddTwoNumbers2 {

    /**
     * 输入情况1：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输入情况2: (5) + (5)
     * 输出：1：7 -> 0 -> 8   2：0 -> 1
     * 原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode total = null;
        boolean shouldUp = false;
        boolean isFirst = true;
        while (l1 != null || l2 != null) {
            int nextVal = 0;
            if (shouldUp) {
                nextVal++;
                shouldUp = false;
            }
            ListNode next = new ListNode();
            int v1 =0,v2=0;
            if(l1!=null){
                v1 = l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                v2 = l2.val;
                l2 = l2.next;
            }
            nextVal += v1+v2;
            if (nextVal >= 10) {
                nextVal = nextVal - 10;
                shouldUp = true;
            }
            next.val = nextVal;
            if (isFirst) {
                isFirst = false;
                total = next;
            } else {
                addNextNode(total, next);
            }
        }
        if(shouldUp){
            addNextNode(total,new ListNode(1,null));
        }
        return total;
    }

    void addNextNode(ListNode target, ListNode node) {
        if (target.next == null) {
            target.next = node;
        } else {
            target = target.next;
            addNextNode(target, node);
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers2 ad2 = new AddTwoNumbers2();
        //ListNode l1 = ad2.new ListNode(2, ad2.new ListNode(4, ad2.new ListNode(3, null)));
        //ListNode l2 = ad2.new ListNode(5, ad2.new ListNode(6, ad2.new ListNode(4, null)));
        ListNode l1 = ad2.new ListNode(2, ad2.new ListNode(4, ad2.new ListNode(3, null)));
        ListNode l2 = ad2.new ListNode(5, null);
        ad2.addTwoNumbers(l1, l2);
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }


}
