import static org.junit.Assert.*;

/**
 * Description of solution: Using Floyd's Cycle Detection algorithm: Use two pointers to move through list. One pointer is one space ahead of other.
 * 							            If the two pointers are at the same node then there is a cycle.
 */


public class CycleLinkedList {

	/*This function returns true if given linked 
	list has a cycle, else returns false. */
	public static boolean hasCycle( Node head) {
		Node temp = head;
		while (head != null && temp != null && head.getNext() != null) {
			head = head.getNext(); 
			temp = head.getNext().getNext();
			if (head == temp) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node("start");
		Node prev = head;
		for (int i =0; i<4; i++) {
			Node temp = new Node(Integer.toString(i));
			prev.setNext(temp);
			prev=temp;
		}

		boolean b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, false);
		System.out.println("Success!");

		prev.setNext(head.getNext());

		b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, true);
		System.out.println("Success!");

	}

}
