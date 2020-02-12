package linkedlist;

/*
Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortListSolution {

    public ListNode sortList2(ListNode head){
        if (head==null || head.next==null) return head;
        return null;
    }

    //归并排序,但是使用递归不符合空间O(1)要求，下面学习使用bottom-to-top方法
    ListNode sortList(ListNode head) {
        if (head==null || head.next==null) return head;
        //找到中间节点，分成两段递归归并
        ListNode slow=head, fast=head.next;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode left=sortList(slow.next);
        slow.next=null;
        ListNode right=sortList(head);
        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode preHead=new ListNode(0), node=preHead;
        while ( left!=null && right!=null ){
            if (left.val<right.val){
                node.next=left;
                left=left.next;
            }else{
                node.next=right;
                right=right.next;
            }
            node=node.next;
        }
        if (left!=null)
            node.next=left;
        if (right!=null)
            node.next=right;
        return preHead.next;
    }
}
