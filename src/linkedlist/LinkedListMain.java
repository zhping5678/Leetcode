package linkedlist;

public class LinkedListMain {

    public static void main(String[] args){
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(9);

        ListNode head2 = new ListNode(1);
        System.out.println(printList(new AddTwoNumbers().addTwoNumbers(head1, head2)));
    }

    private static String printList(ListNode head){
        if (head==null){
            return "NULL";
        }
        StringBuilder builder=new StringBuilder();
        while (head!=null){
            builder.append(head.val).append("->");
            head=head.next;
        }
        return builder.toString();
    }
}
