interface BT {
    public int height();
    public String toString();
    public boolean isBST();
    
    /**
     * Helper function for isBST to satisfy the restrictions. Returns -1 the
     *  node's value is less than n, 0 if it's Nil or equal, and 1 if it's
     *  greater.
     */
    public int compare(int n);
    
    /**
     * Insert a key into the tree. For recursion to work, we need to be able to replace Nil with a node, so it returns a BT.
     */
    public BT insert(int n);
    
    /**
     * Remove a key from the tree. Returns the new tree.
     */
    public BT remove(int n);
    
    /**
     * Find the node with the smallest key in the tree. This will always be a node, never nil.
     */
    public Node min();
    
    public static class Nil implements BT {
        public static final Nil nil = new Nil();
        private Nil() {}
        
        public int height() {
            return -1;
        }
        
        public boolean isBST() {
            return true;
        }
        
        public int compare(int n) {
            return 0;
        }
        
        public BT insert(int n) {
            return new Node(n, nil, nil);
        }
        
        public BT remove(int n) {
            throw new RuntimeException("Key not found");
        }
        
        public Node min() {
            throw new RuntimeException("Empty tree");
        }
        
        public String toString() {
            return "Nil";
        }
    }
    
    public static class Node implements BT {
        public int num;
        private BT left, right;
        
        public Node(int n, BT l, BT r) {
            num = n;
            left = l;
            right = r;
        }
        
        public int height() {
            int lh = left.height();
            int rh = right.height();
            return (lh < rh ? rh : lh) + 1;
        }
        
        // Restrictions: You are not allowed to use loops or checking if a reference is null or not.
        public boolean isBST() {
            return
                left.compare(num) <= 0 &&
                right.compare(num) >= 0 &&
                left.isBST() && right.isBST();
        }
        
        public int compare(int n) {
            // Slightly overengineered - HW def says we can assume unique keys, so 0 will never happen
            return num < n ? -1 : num > n ? 1 : 0;
        }
        
        public BT insert(int n) {
            if(n <= num) {
                left = left.insert(n);
            }
            else {
                right = right.insert(n);
            }
            // Always return this for nodes
            return this;
        }
        
        public Node min() {
            return left.compare(num) < 0? left.min() : this;
        }
        
        public BT remove(int n) {
            // Found the key, replace with the minimum of the right subtree
            if(num == n) {
                Node succ = right.min();
                succ.left = left;
                return succ;
            }
            // Otherwise, recurse
            else if(n < num) {
                left = left.remove(n);
            }
            else {
                right = right.remove(n);
            }
            // This tree level didn't change
            return this;
        }
        
        public String toString() {
            return "Node(" + num + ", " + left + ", " + right + ")";
        }
    }
    
    // Alias for ease of use
    public static final Nil nil = Nil.nil;
    
    public static BT fromArray(int[] arr) {
        BT tree = nil;
        for(int v : arr) {
            tree = tree.insert(v);
        }
        return tree;
    }
    
    public static void main(String[] args) {
        BT tree = new Node(3, new Node(1, nil, new Node(4, nil, nil)), new Node(2, nil, nil));
        System.out.println(tree.height());
        System.out.println(tree);
    }
}