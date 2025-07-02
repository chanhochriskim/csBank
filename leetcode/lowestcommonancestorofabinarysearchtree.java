/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                // root val bigger than both of the nodes. move it to the left.
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                // root val smaller than both. gotta move it to the right.
                root = root.right;
            } else {
                // perfectly balanced. return it
                return root;
            }
        }
        return root;
    }
}
