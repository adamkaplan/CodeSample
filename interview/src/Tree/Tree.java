package Tree;

import java.util.Stack;

public class Tree {
	Node head;

	public Tree() {
		this.head = null;
	}

	public Tree(Node node) {
		this.head = node;
	}

	public void inorder(Node node) {
		if (node == null) {
			return;
		}
		inorder(node.left);
		System.out.println(node.data);
		inorder(node.right);
	}

	public void preorder(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.data);
		preorder(node.left);
		preorder(node.right);
	}

	public void postorder(Node node) {
		if (node == null) {
			return;
		}
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.data);
	}
	
	public void postorderNonRecrursive(Node node) {
		if(node == null) {
			return;
		}
		Stack<Node> st = new Stack<>();
		st.push(node);
		Node currNode = null;
		while(!st.isEmpty()) {
			currNode = st.peek();
			if(currNode.right != null) {
				st.push(currNode.right);
			}
			if(currNode.left != null) {
				st.push(currNode.left);
			}
			if(!st.isEmpty())
				currNode = st.peek();
			else
				break;
			if(currNode.left == null && currNode.right == null) {
				currNode = st.pop();
				System.out.println(currNode.data);
				while(!st.isEmpty() && st.peek().right == currNode) {
					currNode = st.pop();
					System.out.println(currNode.data);
				}
			}
		}
	}
	
	public void preorderNonRecrursive(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> st = new Stack<>();
		st.push(node);
		System.out.println(st.peek().data);
		while (!st.isEmpty()) {
			Node currNode = st.peek();
			while (currNode.left != null) {
				currNode = currNode.left;
				st.push(currNode);
				System.out.println(st.peek().data);
			}
			while (currNode.right == null && !st.isEmpty()) {
				st.pop();
				if (!st.isEmpty())
					currNode = st.peek();
			}
			if (currNode.right != null) {
				st.pop();
				st.push(currNode.right);
				System.out.println(st.peek().data);
			}
		}
	}
	
	public void inorderNonRecrursive(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> st = new Stack<>();
		st.push(node);
		while (!st.isEmpty()) {
			Node currNode = st.peek();
			while (currNode.left != null) {
				currNode = currNode.left;
				st.push(currNode);
			}
			while (currNode.right == null && !st.isEmpty()) {
				System.out.println(currNode.data);
				st.pop();
				if (!st.isEmpty())
					currNode = st.peek();
			}
			if (currNode.right != null) {
				System.out.println(st.pop().data);
				st.push(currNode.right);
			}
		}
	}

	public void add(Node node) {
		if (this.head == null) {
			this.head = node;
			return;
		}
		Node temp = this.head;
		while (true) {
			if (node.data > temp.data) {
				if (temp.right == null) {
					temp.right = node;
					break;
				}
				temp = temp.right;
			} else {
				if (temp.left == null) {
					temp.left = node;
					break;
				}
				temp = temp.left;
			}
		}
	}

	public int numLeaves(Node node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}
		return numLeaves(node.left) + numLeaves(node.right);
	}

	public int depth(Node node) {
		if (node == null) {
			return -1;
		}
		return 1 + Math.max(depth(node.left), depth(node.right));
	}

	public static Node minDepthTreeMaker(int sorted[], int lo, int hi) {
		if (lo > hi) {
			return null;
		}
		int mid = lo + (hi - lo) / 2;
		Node temp = new Node(sorted[mid],
				minDepthTreeMaker(sorted, lo, mid - 1), minDepthTreeMaker(
						sorted, mid + 1, hi));
		return temp;
	}
	public static boolean pathFinder(Node node, int search) {
		if(node == null) {
			return false;
		}
		if (node.data == search) {
			System.out.println(node.data);
			return true;
		}
		boolean left = false, right = false;
		left = pathFinder(node.left, search);
		if(left) {
			System.out.println(node.data);
		}
		right = pathFinder(node.right, search);
		if(right) {
			System.out.println(node.data);
		}
		return left || right;
	}
	public static int level(Node root, int n, int height) {
		if(root == null)
			return -1;
		if(root.data == n) 
			return height;
		return Math.max(level(root.left, n, height + 1), level(root.right, n, height + 1));
	}
	public static void mirror(Node root) {
		if(root == null) return;
		root.left = mirrorHelper(root.right);
	}

	private static Node mirrorHelper(Node root) {
		if(root == null || isLeaf(root)) return root;
		Node temp = new Node(root.data, null, null);
		temp.left = mirrorHelper(root.right);
		temp.right = mirrorHelper(root.left);
		return temp;
	}

	private static boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}	
	public static Node LCA(Node root, Node n1, Node n2) {
		if (root == null) {
			return root;
		}
		if (root.data == n1.data) {
			return n1;
		}
		if (root.data == n2.data) {
			return n2;
		}
		Node left = LCA(root.left, n1, n2);
		Node right = LCA(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}
	public static class Height {
		int height;
	}
	public static int diamHelper(Node root, Height h) {
		if(root == null) {
			h.height = 0;
			return 0;
		}
		Height lh = new Height();
		Height rh = new Height();
		lh.height = h.height + 1;
		rh.height = h.height + 1;
		int ldiam = diamHelper(root.left, lh);
		int rdiam = diamHelper(root.right, rh);
		h.height = 1 + Math.max(lh.height, rh.height);
		return Math.max(1 + lh.height + rh.height, Math.max(ldiam, rdiam));		
	}
	public static int diam(Node root) {
		return diamHelper(root, new Height());
	}
	public static Node nextInorderSuccessor(Node root, Node node) {
		if(node.right == null) {
			Node temp = root;
			while(true) {
				if(temp == node) return null;
				if(temp.data > node.data) {
					if(temp.left == null || temp.left.data <= node.data) return temp;
					temp = temp.left;
				} else {
					temp = temp.right;
				}
				if(temp == null) return null;
			}
		} else {
			Node temp = node;
			temp = node.right;
			while(temp.left != null) {
				temp = temp.left;
			}
			return temp;
		}
	}
}
