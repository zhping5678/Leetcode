package linkedlist;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
class AddTwoNumbers {

    /*
     * 我写的蠢顿如pig的方法
     */
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1 || null == l2) return new ListNode(0);
        ListNode result = null;
        ListNode head = null;

        int carryBit = 0; // 是否进位
        int bit, tmp;
        while (l1 != null && l2 != null) {
            tmp = l1.val + l2.val + carryBit;
            if (tmp >= 10) {
                bit = tmp - 10;
                carryBit = 1;
            }else {
                bit = tmp;
                carryBit = 0;
            }
            if (head == null){ // the first
                head = new ListNode(bit);
                result = head;
            } else { // not the first
              head.next = new ListNode(bit);
              head = head.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (null == l1 && null == l2 && carryBit == 1) {
            head.next = new ListNode(1);
        }
        while (null != l1) {
            tmp = l1.val + carryBit;
            if (tmp >= 10) {
                bit = tmp - 10;
                carryBit = 1;
            }else {
                bit = tmp;
                carryBit = 0;
            }
            head.next = new ListNode(bit);
            head = head.next;
            l1 = l1.next;
        }
        while (null != l2) {
            tmp = l2.val + carryBit;
            if (tmp >= 10) {
                bit = tmp - 10;
                carryBit = 1;
            }else {
                bit = tmp;
                carryBit = 0;
            }
            head.next = new ListNode(bit);
            head = head.next;
            l2 = l2.next;
        }
        if (carryBit == 1) {
            head.next = new ListNode(1);
        }
        return result;
    }

    /*
     * 非递归写法
     * 向网友学习（真是死脑筋，为什么一定要返回头节点呢, 可以返回head.next的；一个链表结束了也可以接着计算嘛
     */
    ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        int carryBit = 0;
        while (null != l1 || null != l2 || carryBit != 0 ){
            int val1 = (l1 == null ? 0 : l1.val);
            int val2 = (l2 == null ? 0 : l2.val);
            int sum = val1 + val2 + carryBit;
            carryBit = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }
        return head.next;
    }
}
