// jan 1 - 12:30pm ~ 12:35pm
/* find kth smallest: in-order traversal. 
- set global values (result as -1, count as 0). 
- during helper, 
    - first, traverse all the way to the left.
    - then, do the count++, and if count ==k, return that root.val. otherwise, inOrder(root.right)

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int count = 0; // to track Kth smallest
    private int result = -1; // global return value.

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    public void inOrder(TreeNode root, int k) {
        if (root == null) return;
        inOrder(root.left, k); // go all the way to the left. 
        count++; // start counting now. 
        if (count == k) {
            result = root.val;
            return;
        }
        inOrder(root.right, k);
    }
}
