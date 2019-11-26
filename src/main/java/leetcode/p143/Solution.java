package leetcode.p143;
class Solution {
    int size = 0;

    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        Solution sol = new Solution();
        sol.reorderList(a);
        System.out.println("");
    }

    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode rev = reverseCopy(head);
        ListNode cur_e = head;
        ListNode cur_o = rev;
        boolean toggle = true;
        ListNode cur = cur_e;
        for (int i = 0; i < size; i++){
            if (toggle){
                cur.next = cur_o;
                cur_o = cur_o.next;
            } else {
                cur.next = cur_e;
                cur_e = cur_e.next;
            }
            cur = cur.next;
            toggle = !toggle;
        }
    }

    public ListNode reverseCopy(ListNode head){
        if (head != null)
            size = 1;
        ListNode cur = head;
        ListNode prev = new ListNode(head.val);
        ListNode next = head;
        while (cur.next != null){
            size ++;
            next = new ListNode(cur.next.val);
            next.next = prev;
            prev = cur;
            cur = cur.next;

        }
        return next;
    }
}