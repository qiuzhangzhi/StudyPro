package 链表;

/**
 * @description:
 * @author: sanduo
 * @date: 2021-05-17 19:36
 */
public class 删除排序链表中的重复元素 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = head;
        ListNode end = head.next;

        while (end != null) {
            while (end != null && start.val == end.val) {
                end = end.next;
            }

            start.next = end;
            start = end;
            if (end != null) {
                end = end.next;
            }
        }
        return head;

    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

