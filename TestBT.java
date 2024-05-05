public class TestBT {
    public static void main(String[] args) {
        testHeight();
        testInsert();
        testIsBST();
        testRemove();
        testMin();
    }

    private static void testHeight() {
        BT tree = BT.nil.insert(10).insert(5).insert(15);
        assert tree.height() == 1 : "Incorrect height calculation";
        System.out.println("Height test passed.");
    }

    private static void testInsert() {
        BT tree = BT.nil;
        tree = tree.insert(10);
        tree = tree.insert(20);
        tree = tree.insert(5);
        assert tree.toString().equals("Node(10, Node(5, Nil, Nil), Node(20, Nil, Nil))") : "Insertion failed";
        System.out.println("Insertion test passed.");
    }

    private static void testIsBST() {
        BT tree = BT.nil.insert(10).insert(5).insert(15);
        assert tree.isBST() : "Tree should be BST";
        BT invalidBST = new BT.Node(10, new BT.Node(15, BT.nil, BT.nil), new BT.Node(5, BT.nil, BT.nil));
        assert !invalidBST.isBST() : "Tree should not be BST";
        System.out.println("BST property test passed.");
    }

    private static void testRemove() {
        BT tree = BT.nil.insert(10).insert(5).insert(15);
        tree = tree.remove(5);
        assert tree.toString().equals("Node(10, Nil, Node(15, Nil, Nil))") : "Remove failed";
        System.out.println("Removal test passed.");
    }

    private static void testMin() {
        BT tree = BT.nil.insert(10).insert(5).insert(15);
        BT.Node min = tree.min();
        assert min.num == 5 : "Min function failed";
        System.out.println("Min function test passed.");
    }
}