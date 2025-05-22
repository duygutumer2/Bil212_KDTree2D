// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javafx.geometry.Point2D;
public class Main {
    public static void main(String[] args) {
/*
                KDTree2D tree = new KDTree2D();

                // Test 1: Insert points
                System.out.println("Test 1: Inserting points");
                Point2D p1 = new Point2D(5, 5);
                Point2D p2 = new Point2D(2, 7);
                Point2D p3 = new Point2D(8, 3);
                Point2D p4 = new Point2D(1, 4);
                Point2D p5 = new Point2D(7, 8);
                Point2D p6 = new Point2D(9, 2);

                tree.insert(p1);  // root
                tree.insert(p2);  // left of root
                tree.insert(p3);  // right of root
                tree.insert(p4);  // left of p2
                tree.insert(p5);  // right of p2
                tree.insert(p6);  // right of p3

                System.out.println("Initial tree structure:");
                tree.displayTree();
                System.out.println();

                // Test 2: Search
                System.out.println("Test 2: Searching points");
                Point2D found = tree.search(new Point2D(2, 7));
                System.out.println("Search (2,7): " + (found != null ? "Found" : "Not Found"));
                found = tree.search(new Point2D(10, 10));
                System.out.println("Search (10,10): " + (found != null ? "Found" : "Not Found"));
                System.out.println();

                // Test 3: FindMin
                System.out.println("Test 3: Finding minimum values");
                Point2D minX = tree.findMin(0);
                Point2D minY = tree.findMin(1);
                System.out.println("Minimum X point: " + minX.getX() + "," + minX.getY());
                System.out.println("Minimum Y point: " + minY.getX() + "," + minY.getY());
                System.out.println();

                // Test 4: FindMax
                System.out.println("Test 4: Finding maximum values");
                Point2D maxX = tree.findMax(0);
                Point2D maxY = tree.findMax(1);
                System.out.println("Maximum X point: " + maxX.getX() + "," + maxY.getY());
                System.out.println("Maximum Y point: " + maxY.getX() + "," + maxY.getY());
                System.out.println();

                // Test 5: Rectangular Range Search
                System.out.println("Test 5: Rectangular range search");
                Point2D ll = new Point2D(1, 2);  // lower-left corner
                Point2D ur = new Point2D(8, 8);  // upper-right corner
                System.out.println("Points in rectangle (1,2) to (8,8):");
                for(Point2D p : tree.printRectangularRange(ll, ur)) {
                    System.out.println("(" + p.getX() + "," + p.getY() + ")");
                }
                System.out.println();

                // Test 6: Circular Range Search
                System.out.println("Test 6: Circular range search");
                Point2D center = new Point2D(5, 5);
                double radius = 3.0;
                System.out.println("Points within circle (center: (5,5), radius: 3):");
                for(Point2D p : tree.printCircularRange(center, radius)) {
                    System.out.println("(" + p.getX() + "," + p.getY() + ")");
                }
                System.out.println();

                // Test 7: Remove
                System.out.println("Test 7: Removing points");

                // Test removing leaf node
                System.out.println("Removing leaf node (9,2):");
                tree.remove(new Point2D(9, 2));
                tree.displayTree();
                System.out.println();

                // Test removing node with one child
                System.out.println("Removing node with one child (8,3):");
                tree.remove(new Point2D(8, 3));
                tree.displayTree();
                System.out.println();

                // Test removing node with two children
                System.out.println("Removing node with two children (2,7):");
                tree.remove(new Point2D(2, 7));
                tree.displayTree();
                System.out.println();

                // Test removing root
                System.out.println("Removing root (5,5):");
                tree.remove(new Point2D(5, 5));
                tree.displayTree();
                System.out.println();

*/

            testRemove();
        }

        public static void testRemove() {
            System.out.println("Starting KD-Tree Remove Tests...\n");

            // Test Case 1: Remove leaf node
            System.out.println("Test Case 1: Remove leaf node");
            KDTree2D tree1 = new KDTree2D();
            Point2D[] points1 = {
                    new Point2D(30, 40),
                    new Point2D(5, 25),
                    new Point2D(18, 10)
            };
            for (Point2D p : points1) {
                tree1.insert(p);
            }
            System.out.println("Before removal:");
            tree1.displayTree();
            tree1.remove(new Point2D(18, 10));
            System.out.println("After removing (18, 10):");
            tree1.displayTree();
            System.out.println("Expected: (18, 10) should be removed, tree structure maintained\n");

            // Test Case 2: Remove node with one child
            System.out.println("Test Case 2: Remove node with one child");
            KDTree2D tree2 = new KDTree2D();
            Point2D[] points2 = {
                    new Point2D(30, 40),
                    new Point2D(5, 25),
                    new Point2D(18, 10),
                    new Point2D(3, 15)
            };
            for (Point2D p : points2) {
                tree2.insert(p);
            }
            System.out.println("Before removal:");
            tree2.displayTree();
            tree2.remove(new Point2D(5, 25));
            System.out.println("After removing (5, 25):");
            tree2.displayTree();
            System.out.println("Expected: (5, 25) should be removed, (3, 15) should be properly connected\n");

            // Test Case 3: Remove node with two children
            System.out.println("Test Case 3: Remove node with two children");
            KDTree2D tree3 = new KDTree2D();
            Point2D[] points3 = {
                    new Point2D(30, 40),
                    new Point2D(5, 25),
                    new Point2D(18, 10),
                    new Point2D(3, 15),
                    new Point2D(8, 30)
            };
            for (Point2D p : points3) {
                tree3.insert(p);
            }
            System.out.println("Before removal:");
            tree3.displayTree();
            tree3.remove(new Point2D(5, 25));
            System.out.println("After removing (5, 25):");
            tree3.displayTree();
            System.out.println("Expected: (5, 25) should be removed, tree should be properly restructured\n");

            // Test Case 4: Remove root node
            System.out.println("Test Case 4: Remove root node");
            KDTree2D tree4 = new KDTree2D();
            Point2D[] points4 = {
                    new Point2D(30, 40),
                    new Point2D(5, 25),
                    new Point2D(80, 90)
            };
            for (Point2D p : points4) {
                tree4.insert(p);
            }
            System.out.println("Before removal:");
            tree4.displayTree();
            tree4.remove(new Point2D(30, 40));
            System.out.println("After removing (30, 40):");
            tree4.displayTree();
            System.out.println("Expected: New root should be selected and tree should be properly restructured\n");

            // Test Case 5: Remove non-existent point
            System.out.println("Test Case 5: Remove non-existent point");
            KDTree2D tree5 = new KDTree2D();
            Point2D[] points5 = {
                    new Point2D(30, 40),
                    new Point2D(5, 25)
            };
            for (Point2D p : points5) {
                tree5.insert(p);
            }
            System.out.println("Before removal:");
            tree5.displayTree();
            tree5.remove(new Point2D(99, 99));
            System.out.println("After attempting to remove (99, 99):");
            tree5.displayTree();
            System.out.println("Expected: Tree should remain unchanged\n");

            // Test Case 6: Remove from empty tree
            System.out.println("Test Case 6: Remove from empty tree");
            KDTree2D tree6 = new KDTree2D();
            System.out.println("Before removal:");
            tree6.displayTree();
            tree6.remove(new Point2D(1, 1));
            System.out.println("After attempting to remove (1, 1):");
            tree6.displayTree();
            System.out.println("Expected: No changes, no errors\n");

            // Test Case 7: Remove last remaining node
            System.out.println("Test Case 7: Remove last remaining node");
            KDTree2D tree7 = new KDTree2D();
            tree7.insert(new Point2D(1, 1));
            System.out.println("Before removal:");
            tree7.displayTree();
            tree7.remove(new Point2D(1, 1));
            System.out.println("After removing (1, 1):");
            tree7.displayTree();
            System.out.println("Expected: Tree should be empty\n");

            System.out.println("All remove tests completed.");
        }
}