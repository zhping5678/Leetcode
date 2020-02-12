package randomlinkedlist;

/*
A linked list is given such that each node contains an additional random pointer which could point to any node
in the list or null.
Return a deep copy of the list.
 */
public class CopyRandomListSolution {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head==null){
            return null;
        }
        copyNode(head);
        copyRandom(head);
        return splitList(head);
    }

    private void copyNode(RandomListNode head){
        RandomListNode node=head;
        while (node!=null){
            RandomListNode newNode=new RandomListNode(node.label);
            newNode.next=node.next;
            node.next=newNode;
            node=newNode.next;
        }
    }

    private void copyRandom(RandomListNode head){
        RandomListNode node=head;
        while (node!=null){
            if (node.random!=null){
                node.next.random=node.random.next;
            }
            node=node.next.next;
        }
    }

    private RandomListNode splitList(RandomListNode head){
        RandomListNode nodeOld=head;
        RandomListNode newHead=head.next, nodeNew=head.next;
        nodeOld.next=nodeNew.next;
        nodeOld=nodeOld.next;
        while (nodeOld!=null ){
            nodeNew.next=nodeOld.next;
            nodeNew=nodeOld.next;

            nodeOld.next=nodeNew.next;
            nodeOld=nodeOld.next;
        }
        return newHead;
    }
}
