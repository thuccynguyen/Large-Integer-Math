package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		// TODO
		 
		for (int i = 0; i < input.length(); i++) {
			LLNode<Integer> temp = new LLNode<Integer>();
			temp.data = input.charAt(i)-'0';
			temp.link = head;
			head = temp;
			size++;
		}
		
		
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		// TODO
		
		if (head.link == null) {
			head.data = 0;
			size = 1;
			return this;
			
		}
		
		LLNode<Integer> tempNode = head.link;
		head.link = null;
		head = tempNode;
		size = size-1;
		return this;
		
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		// TODO
		
		if (head.link == null && head.data == 0) {
			return this;
		}
		
		insertHead(0);
		size++;
		return this;
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  remainder over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		// TODO
		
		/**
		LargeInteger res = new LargeInteger();
		int sum=0;
		int remainder = 0;
		LLNode<Integer> result = null;
		LLNode<Integer> prev = null;
		LLNode<Integer> temp = null;
		LLNode<Integer> thisCurr = this.head;
		LLNode<Integer> thatCurr = that.head;
	
			while (thisCurr!=null || thatCurr!=null) {
			
				res.head = result;
				sum = remainder + (thisCurr != null ? thisCurr.data : 0) + (thatCurr != null ? thatCurr.data : 0); 
				remainder = sum/10;
				sum = sum%10;
				temp = new LLNode<Integer>(sum,null);
				
				if (result == null) {
					result = temp;
					res.size++;
				}
				
				else {
					prev.link = temp;
					res.size++;
				}
				prev = temp;
				
				if (thisCurr != null) {
				thisCurr = thisCurr.link;	
				}
				if (thatCurr != null) {
				thatCurr = thatCurr.link;
				}
			
			}
			
			if (remainder > 0) {
				temp.link = new LLNode<Integer>(remainder,null);
				res.size++;
			}
			
			return res;	
			}
		
		**/
		
		LargeInteger large;
		LargeInteger small;
		LargeInteger result = new LargeInteger();
		int remainder = 0;
		int sum = 0;
		if (this.size() > that.size()) {
			large = this;
			small = that;
		}

		else {
			large = that;
			small = this;
		}

		LLNode<Integer> currlarge = large.head;
		LLNode<Integer> currSmall = small.head;
		LLNode<Integer> currresult = null;
		if (currlarge == null) {
			LLNode<Integer> zero = new LLNode<>();
			zero.data=0;
			result.head = zero;
			return result;
		}

		while (currlarge != null) {
			if (currSmall == null) {
				currSmall = new LLNode<Integer>(0,null);
			}
				sum = currlarge.data + currSmall.data + remainder;
				remainder = sum/10;
			if (currresult == null) {
				result.head = new LLNode<Integer>(sum%10,null);
				currresult = result.head;
				currlarge = currlarge.link;
				currSmall = currSmall.link;
				result.size = 1;
			continue;
			}

			else {
				currresult.link = new LLNode<Integer>(sum%10,null);
				currresult = currresult.link;
				currlarge = currlarge.link;
				currSmall = currSmall.link;
			}

			result.size++;
			}

			if (remainder!=0) {
				currresult.link = new LLNode<Integer>(remainder,null);
				result.size++;
			}
				return result;
			}
		
		
	
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		// TODO
		
		LargeInteger res;
		
		if (x == 0) {
			res = new LargeInteger("0");
			
		}
		
		else {
		
		res = new LargeInteger(this.toString());
		
		for (int i = 1; i < x; i++) {
			
			res = res.add(this);
		
			}
		}
		
		return res;
		}
		
	

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		// TODO
		String nodeString = "";
		
		if (curr_node == null) {
			return "";
		}
		
		return toString(curr_node.link) + curr_node.data.toString();
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
	
	// add to front of linked list
	public void insertHead(int value) {
		LLNode<Integer> tempNode = new LLNode(value,null);
		tempNode.link = head;
		head = tempNode;
	}

	// print linked list
	public void print(LLNode<Integer> head) {
		while (head!= null) {
			System.out.print(head.data + " ");
			head = head.link;
		}
	System.out.println("");
		
	}
}
